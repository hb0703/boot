package com.hb.demo.WorkScheduler;

import com.hb.demo.entity.Activity;
import com.hb.demo.listener.ActivityAutoFlushListener;
import com.hb.demo.service.ActivityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
public class ActivityWorkScheduler {

    RedisTemplate redisTemplate;

    ActivityService activityService;

    private static Logger logger = LoggerFactory.getLogger(ActivityAutoFlushListener.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    //每隔2秒执行一次
    @Scheduled(fixedRate = 1000)
    public void ActivityAutoFlush() {
        List<Activity> values = redisTemplate.opsForHash().values("activity:");
        if(!Objects.isNull(values) && values.size() > 0) {
            for (Activity value : values) {
                executorService.execute(()->{
                    LocalDateTime activityStart = value.getActivityStart();
                    LocalDateTime activityEnd = value.getActivityEnd();
                    //logger.info("当前线程为{}",Thread.currentThread().getName());
                    //logger.info("活动开始时间为;{},活动结束时间为;{}",activityStart,activityEnd);
                    LocalDateTime now = LocalDateTime.now();
                    if(now.isAfter(activityStart) && now.isBefore(activityEnd) && value.getActivityStatus() != 1) {
                        logger.info("活动开启了");
                        //修改状态
                        value.setActivityStatus(1);
                        activityService.updateById(value);
                        redisTemplate.opsForHash().put("activity:",value.getId(),value);
                    }
                    if(now.isAfter(activityEnd) && value.getActivityStatus() != 2) {
                        logger.info("活动关闭了");
                        //修改状态
                        value.setActivityStatus(2);
                        activityService.updateById(value);
                        redisTemplate.opsForHash().put("activity:",value.getId(),value);
                    }

                });
            }
        }
    }


}

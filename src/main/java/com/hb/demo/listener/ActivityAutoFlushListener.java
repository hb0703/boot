package com.hb.demo.listener;

import com.hb.demo.entity.Activity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
public class ActivityAutoFlushListener implements ApplicationContextAware {

    RedisTemplate redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(ActivityAutoFlushListener.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        List<Activity> values = redisTemplate.opsForHash().values("activity:");
//        if(!Objects.isNull(values) && values.size() > 0) {
//            for (Activity value : values) {
//                executorService.execute(()->{
//                    logger.info("当前线程为{}",Thread.currentThread().getName());
//                    LocalDateTime activityStart = value.getActivityStart();
//                    LocalDateTime activityEnd = value.getActivityEnd();
//                    logger.info("活动开始时间为;{},活动结束时间为;{}",activityStart,activityEnd);
//
//                });
//            }
//        }

    }
}

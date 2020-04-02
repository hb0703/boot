package com.hb.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb.demo.entity.Activity;
import com.hb.demo.entity.User;
import com.hb.demo.exception.CommonBusinessException;
import com.hb.demo.mapper.ActivityMapper;
import com.hb.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService extends ServiceImpl<ActivityMapper, Activity> {

    RedisTemplate redisTemplate;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveActivity() {
        for (int i = 1; i < 21; i++) {
            Activity activity = Activity.builder().activityName("测试活动").activityDesc("测试")
                    .activityStatus(0).activityType(1).createBy("admin").createDate(LocalDateTime.now()).
                            activityStart(LocalDateTime.now().plusSeconds(10 + i * 10))
                    .activityEnd(LocalDateTime.now().plusSeconds(20 + i * 10)).build();
            baseMapper.insert(activity);
            redisTemplate.opsForHash().put("activity:",activity.getId(),activity);

        }

    }

    public Activity findById(Long id) {
        return baseMapper.selectById(id);
    }
}

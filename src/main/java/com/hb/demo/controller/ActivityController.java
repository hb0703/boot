package com.hb.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb.demo.api.R;
import com.hb.demo.common.annotation.NoRepeatSubmit;
import com.hb.demo.common.annotation.RequestLog;
import com.hb.demo.entity.Activity;
import com.hb.demo.entity.User;
import com.hb.demo.entity.mongo.Mongolog;
import com.hb.demo.service.ActivityService;
import com.hb.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ActivityController {

    ActivityService activityService;

    @GetMapping("/activity")
    public void test() {
        for (int i = 0; i < 100; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                }
            });
            th.start();

        }
        //第二种方式
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName());
            });
        }

        //第三种方式
        for (int i = 0; i < 100; i++) {
            Runnable r = () ->{
                System.out.println(Thread.currentThread().getName());
            };
            new Thread(r).start();
        }
    }

    @GetMapping("/saveActivity")
    public void saveActivity() {
        activityService.saveActivity();

    }

    @GetMapping("/findActivityById")
    public Activity findById(@RequestParam("id")Long id) {
        return activityService.findById(id);

    }


}

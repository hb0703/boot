package com.hb.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb.demo.api.R;
import com.hb.demo.common.annotation.NoRepeatSubmit;
import com.hb.demo.common.annotation.RequestLog;
import com.hb.demo.entity.User;
import com.hb.demo.entity.mongo.Mongolog;
import com.hb.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {

    UserService userService;

    MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }

    @GetMapping("/findById")
    public R findById(@RequestParam("id")Long id) {
        return R.success(userService.findById(id),"获取成功！");
    }

    @GetMapping("/findUser")
    public R<List<User>> findUser() {
        return R.success(userService.findUser(),"获取成功");
    }

    @GetMapping("/findUserPage")
    public R<IPage<User>> findUserPage(
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "userName",required = false) String userName
    ) {
        return R.success(userService.findUserPage(pageNo,pageSize,userName),"获取成功");
    }

    @GetMapping("/saveUser")
    @RequestLog(interfaceName = "保存用户",model = "用户模块",isSaveRequestData = true,requestUrl = "/saveUser")
    public void saveUser() {
        userService.saveUser();
    }


    @GetMapping("/mogotest")
    public void mogotest() {
        Mongolog mongolog1 = Mongolog.builder().logName("测试1").build();
        Mongolog mongolog = mongoTemplate.insert(mongolog1, "mongolog");
        System.out.println(mongolog);
    }

    @GetMapping("/repeatSaveUser")
    @NoRepeatSubmit(timeout = 4000)
    public void repeatSaveUser() {
        userService.repeatSaveUser();
    }

}

package com.hb.demo.service;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb.demo.entity.User;
import com.hb.demo.exception.CommonBusinessException;
import com.hb.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService extends ServiceImpl<UserMapper,User> {


    public List<User> findUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        List<User> users = baseMapper.selectList(queryWrapper);
        return users;
    }


    public IPage<User> findUserPage(Integer pageNo, Integer pageSize, String userName) {
        IPage<User> page = new Page<>(pageNo, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(userName)) {
            wrapper.setEntity(User.builder().userName(userName).build());
        }
        return baseMapper.selectPage(page,wrapper);
    }

    public void saveUser() {
        try{
            int i = 10 / 0;
        }catch (Exception e) {
            throw new CommonBusinessException();
        }
    }
}

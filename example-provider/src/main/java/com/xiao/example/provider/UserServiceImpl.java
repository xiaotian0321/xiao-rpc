package com.xiao.example.provider;

import com.xiao.example.common.model.User;
import com.xiao.example.common.service.UserService;

/**
 * @author tian.xiao
 * @title UserServiceImpl
 * @create 2025/6/18 13:21
 */

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名:" + user.getUserName());
        return user;
    }
}

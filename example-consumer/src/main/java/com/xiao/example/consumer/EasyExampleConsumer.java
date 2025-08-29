package com.xiao.example.consumer;

import com.xiao.example.common.model.User;
import com.xiao.example.common.service.UserService;
import com.xiao.myrpc.proxy.ServiceProxyFactory;

/**
 * @author tian.xiao
 * @title EasyExampleConsumer
 * @create 2025/6/18 15:58
 */

public class EasyExampleConsumer {
    public static void main(String[] args) {
        // todo 需要获取userService的实现类对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setUserName("xiaoyao");

        User resUser = userService.getUser(user);

        if (resUser != null) {
            System.out.println(resUser.getUserName());
        } else {
            System.out.println("resUser == null");
        }

        short userId = userService.getUserId();
        System.out.println("userId: "+ userId);
    }

}

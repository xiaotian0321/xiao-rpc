package com.xiao.example.common.service;

import com.xiao.example.common.model.User;

public interface UserService {

    User getUser(User user);

    default short getUserId(){
        return 1;
    }
}

package com.xiao.example.common.model;

import java.io.Serializable;

/**
 * @author tian.xiao
 * @title User
 * @create 2025/6/18 12:58
 */

public class User implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

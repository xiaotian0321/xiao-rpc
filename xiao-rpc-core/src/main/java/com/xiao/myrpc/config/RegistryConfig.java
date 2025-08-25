package com.xiao.myrpc.config;

import lombok.Data;

/**
 * @author tian.xiao
 * @title RegistryConfig
 * @create 2025/8/8 18:23
 */

@Data
public class RegistryConfig {

    private String registry = "etcd";

    private String address = "http://172.20.90.99:2380";

    private String username;

    private String password;

    private Long timeout = 10000L;
}

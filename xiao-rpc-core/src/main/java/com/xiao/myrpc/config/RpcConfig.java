package com.xiao.myrpc.config;

import com.xiao.myrpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * 框架的配置
 * @author tian.xiao
 * @title RpcConfig
 * @create 2025/6/21 12:39
 */

@Data
public class RpcConfig {

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();

    /**
     * 名称
     */
    private String name = "xiao-rpc";

    /**
     * 版本
     */
    private String version = "1.0";

    /**
     * 服务主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务端口
     */
    private Integer serverPort = 8080;

    /**
     * 开启模拟调用
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

}

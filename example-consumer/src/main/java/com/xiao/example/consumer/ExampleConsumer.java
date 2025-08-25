package com.xiao.example.consumer;

import com.xiao.myrpc.RpcApplication;
import com.xiao.myrpc.config.RpcConfig;
import com.xiao.myrpc.utils.ConfigUtils;

/**
 * @author tian.xiao
 * @title ExampleConsumer
 * @create 2025/6/21 17:12
 */

public class ExampleConsumer {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig);
    }
}

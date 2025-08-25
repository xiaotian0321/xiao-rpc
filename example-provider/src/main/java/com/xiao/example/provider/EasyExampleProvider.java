package com.xiao.example.provider;

/**
 * @author tian.xiao
 * @title EasyExampleProvider
 * @create 2025/6/18 15:56
 */


import com.xiao.example.common.service.UserService;
import com.xiao.myrpc.RpcApplication;
import com.xiao.myrpc.regitry.LocalRegistry;
import com.xiao.myrpc.server.VertxHttpServer;

/**
 * 简易服务提供者启动类
 */
public class EasyExampleProvider {
    public static void main(String[] args) {

        // rpc框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}

package com.xiao.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xiao.example.common.model.User;
import com.xiao.example.common.service.UserService;
import com.xiao.myrpc.model.RpcRequest;
import com.xiao.myrpc.model.RpcResponse;
import com.xiao.myrpc.serializer.JdkSerializer;
import com.xiao.myrpc.serializer.Serializer;

import java.io.IOException;

/**
 * 静态代理
 * @author tian.xiao
 * @title UserServiceProxy
 * @create 2025/6/19 22:05
 */

public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {

        Serializer serializer =  new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serverName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .arguments(new Object[]{user})
                .build();

        try {
            byte[] bodyBytes  = serializer.serialize(rpcRequest);
            byte[] res;
            try(HttpResponse response = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                res = response.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(res, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}

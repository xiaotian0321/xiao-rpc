package com.xiao.myrpc.proxy;

/**
 * @author tian.xiao
 * @title ServiceProxy
 * @create 2025/6/20 11:23
 */

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xiao.myrpc.model.RpcRequest;
import com.xiao.myrpc.model.RpcResponse;
import com.xiao.myrpc.serializer.JdkSerializer;
import com.xiao.myrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        /**
         * 指定序列化器
         */
        Serializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest request = RpcRequest.builder()
                .serverName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .arguments(args)
                .build();

        try {
            byte[] bytes = serializer.serializer(request);

            // todo 地址为固定的，这部分需要服务注册中心来发现和获取
            try (HttpResponse response = HttpRequest.post("http://localhost:8080").body(bytes)
                         .execute()) {
                byte[] result = response.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

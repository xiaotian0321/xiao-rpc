package com.xiao.myrpc.server;

import com.xiao.myrpc.model.RpcRequest;
import com.xiao.myrpc.model.RpcResponse;
import com.xiao.myrpc.regitry.LocalRegistry;
import com.xiao.myrpc.serializer.JdkSerializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author tian.xiao
 * @title HttpServerHandler
 * @create 2025/6/19 16:19
 */

public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final JdkSerializer jdkSerializer = new JdkSerializer();

        System.out.println("接收到的请求：" + request.method() + " " + request.uri());
        // 异步处理请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;

            try {
                rpcRequest = jdkSerializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null!");
                doResponse(request,rpcResponse,jdkSerializer);
                return;
            }

            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServerName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result =  method.invoke(implClass.newInstance(), rpcRequest.getArguments());

                // 封装返回
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request,rpcResponse,jdkSerializer);
        });
    }

    /**
     * 响应
     * @param request
     * @param rpcResponse
     * @param jdkSerializer
     */
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, JdkSerializer jdkSerializer) {
        HttpServerResponse response = request.response()
                .putHeader("content-type", "application/json");

        try {
            byte[] serialized = jdkSerializer.serializer(rpcResponse);
            response.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            response.end(Buffer.buffer());
        }
    }
}

package com.xiao.myrpc.server;

import io.vertx.core.Vertx;

/**
 * @author tian.xiao
 * @title VertxHttpServer
 * @create 2025/6/18 18:55
 */

public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        // 创建vertx实例
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();

        // 监听端口并处理请求
//        httpServer.requestHandler(request -> {
//
//            System.out.println("request: " + request.method() + " " + request.uri());
//
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("welcome to vertx HttpServer!");
//        });

        httpServer.requestHandler(new HttpServerHandler());

        httpServer.listen(port,result -> {
            if (result.succeeded()){
                System.out.println("server is listen on port: " + port);
            } else {
                System.out.println("failed to start serve: " + result.cause());
            }
        });
    }
}

package com.xiao.myrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author tian.xiao
 * @title ServiceProxyFactory
 * @create 2025/6/20 12:18
 */

public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}

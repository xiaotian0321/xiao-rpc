package com.xiao.myrpc.proxy;

import com.xiao.myrpc.RpcApplication;
import com.xiao.myrpc.config.RpcConfig;
import com.xiao.myrpc.utils.ConfigUtils;

import java.lang.reflect.Proxy;

/**
 * @author tian.xiao
 * @title ServiceProxyFactory
 * @create 2025/6/20 12:18
 */

public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass){
        if (RpcApplication.getRpcConfig().isMock()){
            return getMockServiceProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }


    public static <T> T getMockServiceProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MockServiceProxy());
    }
}

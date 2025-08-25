package com.xiao.myrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tian.xiao
 * @title MockServiceProxy
 * @create 2025/6/23 22:39
 */

@Slf4j
public class MockServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Class<?> returnType = method.getReturnType();
        log.info("mock invoke：{}",method.getName());
        return getDefaultObject(returnType);
    }

    private Object getDefaultObject(Class<?> returnType) {
        // 基本数据类型
        if (returnType.isPrimitive()){
            if (returnType == boolean.class){
                return false;
            } else if (returnType == short.class) {
                return (short) 0;
            } else if (returnType ==int.class){
                return 0;
            } else if (returnType == long.class){
                return 0L;
            }
        }
        // 对象类型
        return null;
    }
}

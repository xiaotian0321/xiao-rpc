package com.xiao.myrpc.regitry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tian.xiao
 * @title Localregistry
 * @create 2025/6/18 21:37
 */

public class LocalRegistry {

    /**
     * 注册信息存储
     * @date 2025/6/18 21:40
     */
    private static final Map<String,Class<?>> map = new HashMap<>();


    /** 注册服务
     * @param serverName
     * @param implClass
     */
    public static void register(String serverName, Class<?> implClass){
        map.put(serverName, implClass);
    }


    /** 获取服务
     * @param serverName
     * @return
     */
    public static Class<?> get(String serverName){
        return map.get(serverName);
    }

    /**
     * 移除服务
     * @param serverName
     */
    public static void remove(String serverName){
        map.remove(serverName);
    }

}

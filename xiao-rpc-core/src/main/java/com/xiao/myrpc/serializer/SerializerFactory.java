package com.xiao.myrpc.serializer;

import com.xiao.myrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tian.xiao
 * @title SerializerFactory
 * @create 2025/6/30 17:58
 */

public class SerializerFactory {
    private static final Map<String,Serializer> KEY_SERIALIZER_MAP = new HashMap<String,Serializer>(){{
        put(SerializerKeys.JDK,new JdkSerializer());
        put(SerializerKeys.JSON,new JsonSerializer());
        put(SerializerKeys.KRYO,new KryoSerializer());
        put(SerializerKeys.HESSIAN,new HessianSerializer());
    }};

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}

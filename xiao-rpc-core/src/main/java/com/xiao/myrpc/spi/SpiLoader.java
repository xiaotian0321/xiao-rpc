package com.xiao.myrpc.spi;

import cn.hutool.core.io.resource.ResourceUtil;
import com.xiao.myrpc.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tian.xiao
 * @title SpiLoader
 * @create 2025/7/1 12:58
 */
@Slf4j
public class SpiLoader {
    /**
     * 存贮已加载的类 接口名 => (key => 实现类)
     */
    private static Map<String, Map<String,Class<?>>> loaderMap = new ConcurrentHashMap<>();

    /**
     * 对象实例缓存，类路径 => 对象实例，单例
     */
    private static Map<String,Object> instanceCache = new ConcurrentHashMap<>();

    /**
     * 系统SPI目录
     */
    private static final String RPC_SYSTEM_SPI_DIR = "META-INF/rpc/system/";

    /**
     * 用户自定义的SPI目录
     */
    private static final String RPC_CUSTOM_SPI_DIR = "META-INF/rpc/custom/";

    /**
     * 要扫描的目录
     */
    private static final String[] SCAN_DIRS = new String[]{RPC_SYSTEM_SPI_DIR,RPC_CUSTOM_SPI_DIR};

    /**
     * 动态加载的类列表
     */
    private static final List<Class<?>> LOAD_CLASS_LIST = Arrays.asList(Serializer.class);

    /**
     * 加载所有类型
     */
    public static void loadAll(){
        log.info("加载所有SPI");
        for (Class<?> aClass : LOAD_CLASS_LIST) {
            load(aClass);
        }
    }

    public static void load(Class<?> loadClass) {
        log.info("加载的SPI类型：{}", loadClass.getName());
        // 扫描路径；用户自定义的的SPI优先级高于系统的SPI
        HashMap<String, Class<?>> keyClassMap =  new HashMap<>();
        for (String scanDir : SCAN_DIRS) {
            List<URL> resource = ResourceUtil.getResources(scanDir + loadClass.getName());
            for (URL url : resource) {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] array = line.split("=");
                        if (array.length > 1){
                            String key = array[0];
                            String className = array[1];
                            keyClassMap.put(key,Class.forName(className));
                        }
                    }
                } catch (Exception e) {
                    log.error("SPI 加载异常", e);
                }
            }
        }
        loaderMap.put(loadClass.getName(),keyClassMap);
    }

    public static  <T> T getInstance(Class<?> tClass, String key){
        String tClassName = tClass.getName();
        Map<String, Class<?>> keyClassMap = loaderMap.get(tClassName);
        if (keyClassMap == null) {
            throw new RuntimeException(String.format("SpiLoader未加载 %s 的类型",tClassName));
        }
        if (!keyClassMap.containsKey(key)) {
            throw new RuntimeException(String.format("SpiLoader 已加载的 %s 不存在 "));
        }
        // 获取要加载的实现类型
        Class<?> implClass = keyClassMap.get(key);
        String implClassName = implClass.getName();
        if (!instanceCache.containsKey(implClassName)) {
            try {
                instanceCache.put(implClassName,implClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                String errorMsg = String.format("%s 类型实例化失败", implClassName);
                throw new RuntimeException(errorMsg, e);
            }
        }

        return (T) instanceCache.get(implClassName);
    }


}

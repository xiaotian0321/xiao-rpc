package com.xiao.myrpc.regitry;

import com.xiao.myrpc.config.RegistryConfig;
import com.xiao.myrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * @author tian.xiao
 * @title Registry
 * @create 2025/8/11 16:37
 */

public interface Registry {
    /**
     * 初始化
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;


    /**
     * 注销服务（服务端）
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（所有节点，消费端）
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

}

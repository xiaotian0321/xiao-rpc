package com.xiao.myrpc.model;

/**
 * @author tian.xiao
 * @title ServiceMetaInfo
 * @create 2025/8/8 13:12
 */

/**
 * 注册信息
 */
public class ServiceMetaInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本
     */
    private String serviceVersion = "1.0";

    /**
     * 服务域名
     */
    private String serviceHost;

    /**
     * 服务端口
     */
    private Integer servicePort;

    /**
     * 服务分组（未实现）
     */
    private String serviceGroup = "default";


    public String getServiceKey() {
        return String.format("%s:%s",serviceName,serviceVersion);
    }

    public String getServiceNodeKey() {
        return String.format("%s/%s:%s",getServiceKey(),serviceHost,servicePort);
    }

}

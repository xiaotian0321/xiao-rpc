package com.xiao.myrpc.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tian.xiao
 * @title RpcRequest
 * @create 2025/6/19 13:03
 */

@Builder
@Data
public class RpcRequest implements Serializable {


    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 方法名
     */
    private String methodName;


    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;


    /**
     * 参数列表
     */
    private Object[] arguments;
}

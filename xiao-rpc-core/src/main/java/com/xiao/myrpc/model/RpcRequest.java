package com.xiao.myrpc.model;

import com.xiao.myrpc.constant.RpcConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tian.xiao
 * @title RpcRequest
 * @create 2025/6/19 13:03
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 服务版本
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

}

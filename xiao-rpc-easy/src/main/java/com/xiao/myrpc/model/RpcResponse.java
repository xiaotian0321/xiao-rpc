package com.xiao.myrpc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tian.xiao
 * @title RpcResponse
 * @create 2025/6/19 13:09
 */

@Data
public class RpcResponse implements Serializable {

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应数据类型（预留）
     */
    private Class<?> dataType;


    /**
     * 响应信息
     */
    private String message;

    /**
     * 异常信息
     */
    private Exception exception;

}

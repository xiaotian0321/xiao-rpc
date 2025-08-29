package com.xiao.myrpc;

import com.xiao.myrpc.config.RpcConfig;
import com.xiao.myrpc.constant.RpcConstant;
import com.xiao.myrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC框架应用
 * 相当于holder，存放全局使用的变量，双检实现
 * @author tian.xiao
 * @title RpcApplication
 * @create 2025/6/21 16:09
 */

@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，可传入自定义配置
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig){
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}",newRpcConfig.toString());
    }

    public static void init(){
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
            log.error(e.getMessage());
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig(){
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}

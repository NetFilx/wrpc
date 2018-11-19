package com.limbo.wrpc.entity;

import lombok.Data;

/**
 * Created By lianzhao on 2018/10/9
 */
@Data
public class RpcRequest {

    /**
     * 调用id
     */
    private String requestId;

    /**
     * rpc请求类名
     */
    private String className;

    /**
     * rpc请求方法名
     */
    private String methodName;

    /**
     * rpc请求的参数类型
     */
    private Class<?>[] parameterType;

    /**
     * rpc请求的参数
     */
    private Object[] parameters;

}

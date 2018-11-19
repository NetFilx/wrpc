package com.limbo.wrpc.entity;

import lombok.Data;

/**
 * Created By lianzhao on 2018/10/9
 */
@Data
public class RpcResponse {

    /**
     * rpc请求id
     */
    private String requestId;

    /**
     * 出现的异常
     */
    private Throwable throwable;

    /**
     * 返回的结果
     */
    private Object result;

}

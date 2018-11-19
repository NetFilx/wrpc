package com.limbo.wrpc.serialization;

/**
 * 序列化接口
 * Created By lianzhao on 2018/11/15
 */
public interface Serialization {
    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}

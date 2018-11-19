package com.limbo.wrpc.serialization;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * JSON 序列化
 * Created By lianzhao on 2018/11/15
 */
@Component
public class JSONSerializer implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) {
        try{
            return JSON.toJSONBytes(obj);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try{
            return JSON.parseObject(bytes,clazz);
        }catch (Exception e) {
            return null;
        }
    }
}

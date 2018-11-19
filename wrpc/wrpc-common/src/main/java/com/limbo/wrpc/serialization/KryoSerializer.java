package com.limbo.wrpc.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import lombok.Getter;
import lombok.Setter;


/**
 * Kryo 序列化
 * Created By lianzhao on 2018/11/15
 */
public class KryoSerializer implements Serialization {

    /**
     * 序列化buffer size
     */
    private final static int BUFFER_SIZE = 300;

    private final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(clazz, new BeanSerializer<>(kryo, clazz));
            return kryo;
        }
    };

    @Setter
    @Getter
    private Class<?> clazz = null;
    private final ThreadLocal<Output> outputLocal = new ThreadLocal<>();
    private final ThreadLocal<Input> inputLocal = new ThreadLocal<>();

    public KryoSerializer(Class<?> clazz) {
        this.clazz = clazz;
    }

    private Output getOutput(byte[] bytes) {
        Output output;
        if ((output = outputLocal.get()) == null) {
            output = new Output();
            outputLocal.set(output);
        }
        if (bytes != null) {
            output.setBuffer(bytes);
        }
        return output;
    }

    private Input getInput(byte[] bytes) {
        Input input;
        if ((input = inputLocal.get()) == null) {
            input = new Input();
            inputLocal.set(input);
        }
        if(bytes != null) {
            input.setBuffer(bytes);
        }
        return input;
    }

    @Override
    public <T> byte[] serialize(T obj) {
        Kryo kryo = kryoLocal.get();
        byte[] bytes = new byte[BUFFER_SIZE];
        Output output = getOutput(bytes);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        return bytes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Kryo kryo = kryoLocal.get();
        Input input = getInput(bytes);
        return (T) kryo.readClassAndObject(input);
    }
}

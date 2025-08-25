package com.xiao.myrpc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author tian.xiao
 * @title HessianSerializer
 * @create 2025/6/30 13:23
 */

public class HessianSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(object);

        return byteArrayOutputStream.toByteArray();
    }



    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);

        try {
            // 确保传入类型信息
            Object result = hessianInput.readObject(type);
            return type.cast(result);
        } catch (Exception e) {
            throw new IOException("Failed to deserialize object with Hessian", e);
        } finally {
            hessianInput.close();
        }
    }

}

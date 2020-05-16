package com.jason.jlh.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

/**
 * @title: SecretSerializer
 * @package: com.jason.jlh.common.serialization
 * @description: 敏感信息序列化器
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class SecretSerializer extends StdScalarSerializer<String> {

    public SecretSerializer() {
        super(String.class);
    }

    /**
     * 序列化方法
     * 
     * @param: [str, jsonGenerator, serializerProvider]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("*");
    }
}

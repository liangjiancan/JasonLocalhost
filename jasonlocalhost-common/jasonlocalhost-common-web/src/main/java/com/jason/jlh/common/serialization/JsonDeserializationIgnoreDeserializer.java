package com.jason.jlh.common.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;

/**
 * @title: JsonDeserializationIgnoreDeserializer
 * @package: com.jason.jlh.common.serialization
 * @description: 不进行Json序列化的反序列化器
 * @author:
 * @date: 2020/5/16
 * @version: v1.0
 */
public class JsonDeserializationIgnoreDeserializer extends StdScalarDeserializer {

    public JsonDeserializationIgnoreDeserializer() {
        super(Object.class);
    }

    /**
     * 反序列化方法
     * 
     * @param: [parser, context]
     * @return: java.lang.Object
     * @author:
     * @date: 2020/5/16
     */
    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return null;
    }
}

package com.jason.jlh.common.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;

/**
 * @title: TrimStringDeserializer
 * @package: com.jason.jlh.common.serialization
 * @description: 字符串去前后空格反序列化器
 * @author:
 * @date: 2020/5/16
 * @version: v1.0
 */
public class TrimStringDeserializer extends StringDeserializer {

    /**
     * 反序列化方法
     *
     * @param: [parse, context]
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/16
     */
    @Override
    public String deserialize(JsonParser parse, DeserializationContext context) throws IOException {
        JsonNode node = parse.getCodec().readTree(parse);
        if (node.getNodeType() == JsonNodeType.STRING) {
            String val = node.asText("");
            return val.trim();
        }
        return super.deserialize(parse, context);
    }
}

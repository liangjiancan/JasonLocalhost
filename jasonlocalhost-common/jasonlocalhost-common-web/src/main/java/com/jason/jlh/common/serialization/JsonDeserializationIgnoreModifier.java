package com.jason.jlh.common.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.jason.jlh.common.annotation.JsonDeserializationIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: JsonDeserializationIgnoreModifier
 * @package: com.jason.jlh.common.serialization
 * @description: Json忽略反序列化调整器
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class JsonDeserializationIgnoreModifier extends BeanSerializerModifier {

    /**
     * 改变属性
     *
     * @param: [config, beanDesc, beanProperties]
     * @return: java.util.List<com.fasterxml.jackson.databind.ser.BeanPropertyWriter>
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> result = new ArrayList<>();
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            JsonDeserializationIgnore annotation = writer.getAnnotation(JsonDeserializationIgnore.class);
            if (null == annotation) {
                // 没有@JsonWebIgnore则添加到结果
                result.add(writer);
            }
        }
        return result;
    }
}

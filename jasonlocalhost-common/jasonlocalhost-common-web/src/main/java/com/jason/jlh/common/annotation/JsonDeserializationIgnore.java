package com.jason.jlh.common.annotation;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jason.jlh.common.serialization.JsonDeserializationIgnoreDeserializer;

import java.lang.annotation.*;


/**
 * @title: JsonDeserializationIgnore
 * @package: com.jason.jlh.common.annotation
 * @description: Json反序列化忽略注解, 把Json字符串反序列化时忽略指定字段
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@JacksonAnnotationsInside
@JsonDeserialize(using = JsonDeserializationIgnoreDeserializer.class)
public @interface JsonDeserializationIgnore {
}

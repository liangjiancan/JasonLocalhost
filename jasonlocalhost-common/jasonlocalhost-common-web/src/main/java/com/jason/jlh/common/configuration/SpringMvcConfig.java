package com.jason.jlh.common.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.jason.jlh.common.constant.DateTimeConstant;
import com.jason.jlh.common.serialization.JsonDeserializationIgnoreModifier;
import com.jason.jlh.common.serialization.TrimStringDeserializer;
import com.jason.jlh.common.support.resolver.DateTimeArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

/**
 * @title: SpringMvcConfig
 * @package: com.jason.jlh.common.configuration
 * @description: SpringMVC配置类
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 配置请求消息转换器
     *
     * @param: [converters]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 优先使用该转换器封装String类型
        converters.add(0, jacksonConverter());
    }

    /**
     * jackson转换器
     *
     * @param: []
     * @return: org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Bean
    public MappingJackson2HttpMessageConverter jacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    /**
     * 对象映射器
     *
     * @param: []
     * @return: com.fasterxml.jackson.databind.ObjectMapper
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 如果json字段比较多，而我们对象只需要部分字段，这时反序列化时会报错，加入该配置可解决
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置序列化枚举时使用枚举的toString方法
        // objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 设置默认的Date类型转换
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeConstant.DATE_TIME_FORMAT));
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getDefault());
        // 创建Java时间模块, 设置默认的LocalDateTime的序列化和反序列化器
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 配置序列化器
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.TIME_FORMAT)));
        // 配置反序列化器
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule);
        // 配置字符串反序列化器
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new TrimStringDeserializer());
        objectMapper.registerModule(module);
        // 注册一个带有SerializerModifier的Factory, 配置Json反序列化忽略修正器
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new JsonDeserializationIgnoreModifier()));
        return objectMapper;
    }

    /**
     * 增加参数解析器
     *
     * @param: [argumentResolvers]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        MappingJackson2HttpMessageConverter converter = jacksonConverter();
        // 注册DateTimeResolver处理GET请求的日期参数
        argumentResolvers.add(new DateTimeArgumentResolver(converter));
    }
}

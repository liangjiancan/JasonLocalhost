package com.jason.jlh.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @title: JsonDeserializationIgnore
 * @package: com.jason.jlh.common.annotation
 * @description: 业务类控制器组合注解, 集成了@RestController、@RequestMapping 、@Validated等等的注解
 * @author:
 * @date: 2020/5/31
 * @version: v1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Validated
@RequestMapping
@RestController
public @interface BusinessController {
    
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};
}

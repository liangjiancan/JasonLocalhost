package com.jason.jlh.common.support.resolver;

import com.jason.jlh.common.constant.DateTimeConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @title: DateTimeArgumentResolver
 * @package: com.jason.jlh.common.support.resolver
 * @description: 日期时间解析器, 对Get请求的日期时间进行处理
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class DateTimeArgumentResolver implements HandlerMethodArgumentResolver {

    private AbstractJackson2HttpMessageConverter converter;

    public DateTimeArgumentResolver(AbstractJackson2HttpMessageConverter converter) {
        this.converter = converter;
    }

    /**
     * 定义支持的参数
     *
     * @param: [parameter]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        GetMapping getMapping = parameter.getExecutable().getAnnotation(GetMapping.class);
        RequestMapping requestMapping = parameter.getExecutable().getAnnotation(RequestMapping.class);
        // 支持GET请求的日期参数处理
        if (null != getMapping || (null != requestMapping && containsMethod(requestMapping, RequestMethod.GET))) {
            return parameter.getParameterType() == LocalDateTime.class
                    || parameter.getParameterType() == LocalDate.class
                    || parameter.getParameterType() == LocalTime.class
                    || parameter.getParameterType() == Date.class;
        }
        return false;
    }

    /**
     * 对支持的参数进行解析
     *
     * @param: [parameter, mavContainer, webRequest, binderFactory]
     * @return: java.lang.Object
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String strVal = webRequest.getParameter(parameter.getParameterName());
        if (StringUtils.isBlank(strVal)) {
            return null;
        }
        if (parameter.getParameterType() == Date.class) {
            // Date类型转换时线程不安全，需要每次new一个formatter
            SimpleDateFormat formatter = new SimpleDateFormat(DateTimeConstant.DATE_TIME_FORMAT);
            return formatter.parse(strVal);
        }
        // 其他日期格式通过jackson转换器处理
        return converter.getObjectMapper().convertValue(strVal, parameter.getParameterType());

    }

    /**
     * 判断是否包含指定的请求方法
     *
     * @param: [mapping, requestMethod]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/16
     */
    private static boolean containsMethod(RequestMapping mapping, RequestMethod requestMethod) {
        for (RequestMethod method : mapping.method()) {
            if (requestMethod.equals(method)) {
                return true;
            }
        }
        return false;
    }
}
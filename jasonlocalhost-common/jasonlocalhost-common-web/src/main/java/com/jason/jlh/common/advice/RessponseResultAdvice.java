package com.jason.jlh.common.advice;

import com.jason.jlh.common.pojo.ResponseResult;
import com.jason.jlh.common.support.BaseWebFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @title: RessponseResultAdvice
 * @package: com.jason.jlh.common.advice
 * @description: 返回结果全局处理类
 * @author: huyongjun
 * @date: 2020/5/31
 * @version: v1.0
 */
@ControllerAdvice
public class RessponseResultAdvice implements ResponseBodyAdvice<Object>, BaseWebFunction {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${jlh.module}")
    private String module;

    /**
     * 匿名访问路径
     */
    private static final String ANONYMOUS_PATH = "anonymous";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 响应返回前的处理
     *
     * @param: [body, methodParameter, mediaType, aClass, request, response]
     * @return: java.lang.Object
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        String requestPath = request.getURI().getPath();
        // 封装正常请求
        if (requestPath.startsWith(contextPath + "/" + module)) {
            return wrapResult(body);
        }
        // 封装匿名请求
        if (requestPath.startsWith(contextPath + "/" + ANONYMOUS_PATH)) {
            return wrapResult(body);
        }
        return body;
    }

    /**
     * 对结果进行封装
     *
     * @param: [body]
     * @return: java.lang.Object
     * @author:
     * @date: 2020/5/31
     */
    private Object wrapResult(Object body) {
        if (body instanceof ResponseResult) {
            return body;
        }
        ResponseResult result = new ResponseResult(body);
        result.setUrl(getHttpRequestContent());
        return result;
    }
}

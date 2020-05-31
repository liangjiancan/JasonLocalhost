package com.jason.jlh.common.support;

import com.jason.jlh.common.exception.WebException;
import com.jason.jlh.common.utils.WebUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @title: BaseWebFunction
 * @package: com.jason.jlh.common.support
 * @description: 提供一些Web层的通用方法
 * @author:
 * @date: 2020/5/14
 * @version: v1.0
 */
public interface BaseWebFunction {

    /**
     * 获取Request对象
     *
     * @param: []
     * @return: javax.servlet.http.HttpServletRequest
     * @author:
     * @date: 2020/5/31
     */
    default HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Response对象
     *
     * @param: []
     * @return: javax.servlet.http.HttpServletRequest
     * @author:
     * @date: 2020/5/31
     */
    default HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取Session对象
     *
     * @param: []
     * @return: javax.servlet.http.HttpSession
     * @author:
     * @date: 2020/5/31
     */
    default HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取请求内容, 返回格式如: POST http://127.0.0.1:80/web/selectById HTTP 1.1
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/31
     */
    default String getHttpRequestContent() {
        HttpServletRequest request = getRequest();
        return String.format("%s %s %s", request.getMethod(), request.getRequestURI(), request.getProtocol());
    }

    /**
     * 获取真实请求IP
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/31
     */
    default String getRealRemoteIp() {
        HttpServletRequest request = getRequest();
        return getRemoteIp(request, true);
    }

    /**
     * 获取远程IP
     *
     * @param: [request, tryDetermineFromHeaders 是否尝试从Header中查找常见转发和代理会使用的客户端IP标识]
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/31
     */
    default String getRemoteIp(HttpServletRequest request, boolean tryDetermineFromHeaders) {
        return WebUtil.getRemoteIp(request, tryDetermineFromHeaders);
    }

    /**
     * 判断是否为AJAX请求
     *
     * @param: []
     * @return: void
     * @author:
     * @date: 2020/5/31
     */
    default boolean isAjaxRequest() {
        return WebUtil.isAjaxRequest(getRequest());
    }

    /**
     * 强制把响应设置为no cache方式
     *
     * @param: []
     * @return: void
     * @author:
     * @date: 2020/5/31
     */
    default void setResponseNoCache() {
        WebUtil.setResponseNoCache(getResponse());
    }

    /**
     * 统一抛出WebException异常方法
     *
     * @param: [message]
     * @return: java.lang.RuntimeException
     * @author:
     * @date: 2020/5/14
     */
    default RuntimeException fail(String message) {
        return new WebException(message);
    }

    /**
     * 统一抛出WebException异常方法
     *
     * @param: [message, data]
     * @return: java.lang.RuntimeException
     * @author:
     * @date: 2020/5/14
     */
    default RuntimeException fail(String message, Object data) {
        return new WebException(message, data);
    }
}

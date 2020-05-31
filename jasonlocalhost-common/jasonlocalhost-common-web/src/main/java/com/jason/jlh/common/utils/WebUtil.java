package com.jason.jlh.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @title: WebUtil
 * @package: com.jason.jlh.common.utils
 * @description: WEB工具类
 * @author:
 * @date: 2020/5/31
 * @version: v1.0
 */
@Slf4j
public class WebUtil {

    private WebUtil() {
    }

    /**
     * 把HttpServletRequest中的Header全部提取出来
     * 按“名称-值”作为键值对放进一个Map中, 如果值有多个则会被转换成数组
     *
     * @param: [request]
     * @return: java.util.Map
     * @author:
     * @date: 2020/5/31
     */
    public static Map<String, Object> getHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>(0);
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            List<Object> valueList = new ArrayList<>();
            while (values.hasMoreElements()) {
                Object value = values.nextElement();
                valueList.add(value);
            }
            if (valueList.size() > 0) {
                if (valueList.size() == 1)
                    headers.put(name, valueList.get(0));
                else
                    headers.put(name, valueList.toArray());
            }
        }
        return headers;
    }

    /**
     * 强制把响应设置为no cache方式
     *
     * @param: [response]
     * @return: void
     * @author:
     * @date: 2020/5/31
     */
    public static void setResponseNoCache(HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache,max-age=0");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 根据x-requested-with头是否为XMLHttpRequest判断是否为AJAX请求
     *
     * @param: [request]
     * @return: boolean
     * @author:
     * @date: 2020/5/31
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 从当前HTTP请求中识别客户端IP地址
     *
     * @param: [request, tryDetermineFromHeaders 是否尝试从Header中查找常见转发和代理会使用的客户端IP标识]
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/31
     */
    public static String getRemoteIp(HttpServletRequest request, boolean tryDetermineFromHeaders) {
        if (null == request) {
            return "UNK";  //未知
        }

        String ip = request.getRemoteAddr();
        if (tryDetermineFromHeaders) {
            if (StringUtils.isNotBlank(request.getHeader("$WSRH"))) {
                // 使用WebSphere PlugIn转发时才会使用的特殊Header
                ip = request.getHeader("$WSRH");
            } else if (StringUtils.isNotBlank(request.getHeader("X-FORWARDED-FOR"))) {
                // 以下是一些常见HTTP代理(反向代理)转发时使用的Header
                ip = request.getHeader("X-FORWARDED-FOR");
            } else if (StringUtils.isNotBlank(request.getHeader("X-REAL-IP"))) {
                ip = request.getHeader("X-REAL-IP");
            } else if (StringUtils.isNotBlank(request.getHeader("HTTP_CLIENT_IP"))) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            } else if (StringUtils.isNotBlank(request.getHeader("HTTP_X_FORWARDED_FOR"))) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            } else if (StringUtils.isNotBlank(request.getHeader("HTTP_X_FORWARDED"))) {
                ip = request.getHeader("HTTP_X_FORWARDED");
            } else if (StringUtils.isNotBlank(request.getHeader("HTTP_FORWARDED_FOR"))) {
                ip = request.getHeader("HTTP_FORWARDED_FOR");
            } else if (StringUtils.isNotBlank(request.getHeader("HTTP_FORWARDED"))) {
                ip = request.getHeader("HTTP_FORWARDED");
            }
        }
        return ip;
    }

}

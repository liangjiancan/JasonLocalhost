package com.jason.jlh.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @title: LogUtil
 * @package: com.jason.jlh.common.utils
 * @description: 日志工具类
 * @author: huyongjun
 * @date: 2020/5/25
 * @version: v1.0
 */
@Slf4j
public class LogUtil {

    private static ObjectMapper om = new ObjectMapper();

    /**
     * 最大字符串长度
     */
    private static final int MAX_STRING_LENGTH = 1500;
    private static final String FAIL = " _FAIL_ ";

    private LogUtil() {
    }

    /**
     * 转化为Json字符串
     *
     * @param returns
     * @return
     */
    public static String toJsonString(Object returns) {
        return toJsonString(returns, MAX_STRING_LENGTH);
    }

    /**
     * 转化为Json字符串
     *
     * @param: [returns, maxLength]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/25
     */
    public static String toJsonString(Object returns, int maxLength) {
        try {
            // 如果本身是String，则不用json序列化
            if (returns instanceof String) {
                return limitStringLength((String) returns);
            } else {
                // 非String，尝试使用json序列化
                String result = om.writeValueAsString(returns);
                return limitStringLength(result);
            }
        } catch (Throwable e) {
            log.debug(String.format("序列化对象失败: %s", returns.getClass().getName()));
            return FAIL;
        }
    }

    /**
     * 转化为Json字符串
     *
     * @param: [e]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/25
     */
    public static String toJsonString(Throwable e) {
        try {
            String result = om.writeValueAsString(e.getMessage());
            return limitStringLength(result);
        } catch (Throwable ex) {
            log.debug(String.format("序列化对象失败: %s", ex.getClass().getName()));
            return FAIL;
        }
    }

    /**
     * 限制字符串长度
     *
     * @param: [str]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/24
     */
    public static String limitStringLength(String str) {
        if (null == str) {
            return null;
        }
        if (str.length() > MAX_STRING_LENGTH) {
            return str.substring(0, MAX_STRING_LENGTH - 4) + " ...";
        } else {
            return str;
        }
    }
}

package com.jason.jlh.common.support;

import com.jason.jlh.common.enums.YesornoEnum;
import com.jason.jlh.common.exception.WebException;

/**
 * @title: BaseWebFunction
 * @package: com.jason.jlh.common.support
 * @description: 提供一些Web层的通用方法
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
public interface BaseWebFunction {

    /**
     * 常用常量
     */
    YesornoEnum Yes = YesornoEnum.Yes;
    YesornoEnum No = YesornoEnum.No;

    /**
     * 统一抛出WebException异常方法
     *
     * @param: [message]
     * @return: java.lang.RuntimeException
     * @author: huyongjun
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
     * @author: huyongjun
     * @date: 2020/5/14
     */
    default RuntimeException fail(String message, Object data) {
        return new WebException(message, data);
    }
}

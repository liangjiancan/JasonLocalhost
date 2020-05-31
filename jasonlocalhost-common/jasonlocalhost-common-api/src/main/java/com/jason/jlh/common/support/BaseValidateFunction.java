package com.jason.jlh.common.support;

import com.google.common.base.Strings;
import com.jason.jlh.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @title: BaseValidateFunction
 * @package: com.jason.jlh.common.support
 * @description: 提供一些通用的校验方法
 * @author:
 * @date: 2020/4/20
 * @version: v1.0
 */
public interface BaseValidateFunction {

    /**
     * 断言字符序列内容非且非空字符串, 失败则抛出com.csair.meng.framework.dto.exception.ServiceException
     *
     * @param: [content 校验的字符串]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotBlank(CharSequence content) {
        checkNotBlank(content, "操作失败, 参数不能为空");
    }

    /**
     * 断言字符序列内容非且非空字符串, 失败则抛出自定义的异常消息
     *
     * @param: [content 校验的字符串, errorMessage 校验失败时抛出的异常消息]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotBlank(CharSequence content, String errorMessage) {
        checkNotBlank(content, errorMessage, null);
    }

    /**
     * 断言字符序列内容非且非空字符串, 失败则抛出自定义的格式化异常消息
     *
     * @param: [content 校验的字符串, errorMessageTemplate 校验失败时抛出的异常消息的格式化字符串模板, errorMessageArgs 格式化字符串参数(可变参数)]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotBlank(CharSequence content, String errorMessageTemplate, Object... errorMessageArgs) {
        if (StringUtils.isBlank(content)) {
            throw new ServiceException(errorMessageArgs == null ? errorMessageTemplate : Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 断言对象非空, 失败则抛出com.csair.meng.framework.dto.exception.ServiceException
     *
     * @param: [obj 校验的对象]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotNull(Object obj) {
        checkNotNull(obj, "操作失败, 参数不能为空");
    }

    /**
     * 断言对象非空, 失败则抛出自定义的异常消息
     *
     * @param: [obj 校验的对象, errorMessage 校验失败时抛出的异常消息]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotNull(Object obj, String errorMessage) {
        checkNotNull(obj, errorMessage, null);
    }

    /**
     * 断言对象非空, 失败则抛出自定义的格式化异常消息
     *
     * @param: [obj 校验的对象, errorMessageTemplate 校验失败时抛出的异常消息的格式化字符串模板, errorMessageArgs 格式化字符串参数(可变参数)]
     * @return: void
     * @author:
     * @date: 2020/4/20
     */
    default void checkNotNull(Object obj, String errorMessageTemplate, Object... errorMessageArgs) {
        if (obj == null) {
            throw new ServiceException(errorMessageArgs == null ? errorMessageTemplate : Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 断言Collection容器非空, 失败则抛出com.csair.meng.framework.dto.exception.ServiceException
     *
     * @param: [collection 校验的容器]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Collection collection) {
        checkNotEmpty(collection, "操作失败, 参数不能为空");
    }

    /**
     * 断言Collection容器非空, 失败则抛出自定义的异常消息
     *
     * @param: [collection 校验的容器, errorMessage 校验失败时抛出的异常消息]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Collection collection, String errorMessage) {
        checkNotEmpty(collection, errorMessage, null);
    }

    /**
     * 断言Collection容器非空, 失败则抛出自定义的格式化异常消息
     *
     * @param: [collection 校验的容器, errorMessageTemplate 校验失败时抛出的异常消息的格式化字符串模板, errorMessageArgs 格式化字符串参数(可变参数)]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Collection collection, String errorMessageTemplate, Object... errorMessageArgs) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ServiceException(errorMessageArgs == null ? errorMessageTemplate : Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 断言Map容器非空, 失败则抛出com.csair.meng.framework.dto.exception.ServiceException
     *
     * @param: [map 校验的容器]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Map map) {
        checkNotEmpty(map, "操作失败, 参数不能为空");
    }

    /**
     * 断言Map容器非空, 失败则抛出自定义的异常消息
     *
     * @param: [map 校验的容器, errorMessage 校验失败时抛出的异常消息]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Map map, String errorMessage) {
        checkNotEmpty(map, errorMessage, null);
    }

    /**
     * 断言Map容器非空, 失败则抛出自定义的格式化异常消息
     *
     * @param: [map 校验的容器, errorMessageTemplate 校验失败时抛出的异常消息的格式化字符串模板, errorMessageArgs 格式化字符串参数(可变参数)]
     * @return: void
     * @author:
     * @date: 2020/4/21
     */
    default void checkNotEmpty(Map map, String errorMessageTemplate, Object... errorMessageArgs) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ServiceException(errorMessageArgs == null ? errorMessageTemplate : Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 断言表达式为true, 失败则抛出com.csair.meng.framework.dto.exception.ServiceException
     *
     * @param: [expression 校验的布尔表达式]
     * @return: void
     * @author:
     * @date: 2020/4/23
     */
    default void checkArgument(boolean expression) {
        checkArgument(expression, "操作失败, 校验不通过");
    }

    /**
     * 断言表达式为true, 失败则抛出自定义的异常消息
     *
     * @param: [expression 校验的布尔表达式, errorMessage 校验失败时抛出的异常消息]
     * @return: void
     * @author:
     * @date: 2020/4/23
     */
    default void checkArgument(boolean expression, String errorMessage) {
        checkArgument(expression, errorMessage, null);
    }

    /**
     * 断言表达式为true, 失败则抛出自定义的格式化异常消息
     *
     * @param: [expression 校验的布尔表达式, errorMessageTemplate 校验失败时抛出的异常消息的格式化字符串模板, errorMessageArgs 格式化字符串参数(可变参数)]
     * @return: void
     * @author:
     * @date: 2020/4/23
     */
    default void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new ServiceException(errorMessageArgs == null ? errorMessageTemplate : Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }
}

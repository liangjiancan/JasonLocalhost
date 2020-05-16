package com.jason.jlh.common.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.jason.jlh.common.exception.ServiceException;
import com.jason.jlh.common.pojo.GlobalParam;
import org.springframework.aop.framework.AopContext;

/**
 * @title: BaseServiceFunction
 * @package: com.jason.jlh.common.service
 * @description: 提供一些Service层的通用方法
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface BaseServiceFunction {

    /**
     * 获取用户名
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    default String getUserName() {
        return GlobalParam.getParam().getUserName();
    }

    /**
     * 获取用户ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    default String getUserId() {
        return GlobalParam.getParam().getUserId();
    }

    /**
     * 获取用户会话标识，即userCode+":"+sessionId
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    default String getUserSession() {
        return GlobalParam.getParam().getUserSession();
    }

    /**
     * 获取用户会话ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    default String getSessionId() {
        return GlobalParam.getParam().getSessionId();
    }

    /**
     * 获取客户端IP
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    default String getClientIp() {
        return GlobalParam.getParam().getClientIp();
    }

    /**
     * 是否管理员
     *
     * @param: []
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    default boolean isAdmin() {
        return GlobalParam.getParam().isAdmin();
    }

    /**
     * 判断数据库操作是否成功
     *
     * @param: [result]
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    default boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    /**
     * 统一抛出ServiceException异常方法
     *
     * @param: [message]
     * @return: java.lang.RuntimeException
     * @author:
     * @date: 2020/5/3
     */
    default RuntimeException fail(String message) {
        return new ServiceException(message);
    }

    /**
     * 获得自身的代理
     * 自身的非事务方法调用自身的事务方法时不会开启事务, 需要通过调用自身的代理来开启事务
     * ITrEmployeeService that = that(); that.insert(xxx) //转换成接口，调用public方法
     * TrEmployeeService that = that(); that.insert(xxx) //转换成实现，调用非接口的public方法
     *
     * @param: []
     * @return: T
     * @author:
     * @date: 2020/5/3
     */
    default <T> T that() {
        return (T) AopContext.currentProxy();
    }
}

package com.jason.jlh.common.support;

import com.jason.jlh.common.enums.YesornoEnum;
import org.springframework.aop.framework.AopContext;

/**
 * @title: BaseFunction
 * @package: com.jason.jlh.common.support
 * @description: 提供一些常用的基本函数
 * @author:
 * @date: 2020/5/31
 * @version: v1.0
 */
public interface BaseFunction {

    /**
     * 常用常量
     */
    YesornoEnum Yes = YesornoEnum.Yes;
    YesornoEnum No = YesornoEnum.No;

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

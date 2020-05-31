package com.jason.jlh.common.controller;

import com.jason.jlh.common.service.IBaseService;
import com.jason.jlh.common.support.BaseValidateFunction;
import com.jason.jlh.common.support.BaseWebFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: AbstractCommonController
 * @package: com.jason.jlh.common.controller
 * @description: 通用控制器抽象类
 * @author:
 * @date: 2020/5/14
 * @version: v1.0
 */
@Slf4j
public abstract class AbstractCommonController<Service extends IBaseService> implements BaseWebFunction, BaseValidateFunction {

    @Autowired
    protected Service service;

}

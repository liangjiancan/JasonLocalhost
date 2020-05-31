package com.jason.jlh.common.controller;

import com.jason.jlh.common.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: AbstractExtendController
 * @package: com.jason.jlh.common.controller
 * @description: 通用控制器抽象类
 * @author:
 * @date: 2020/5/14
 * @version: v1.0
 */
@Slf4j
public abstract class AbstractExtendController<Service extends IBaseService> {

    @Autowired
    protected Service service;

}

package com.jason.jlh.common.controller;

import com.jason.jlh.common.enums.YesornoEnum;
import com.jason.jlh.common.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: AbstractCommonController
 * @package: com.jason.jlh.common.controller
 * @description: 通用控制器抽象类
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
@Slf4j
public abstract class AbstractCommonController<IService extends IBaseService> implements BaseController, BaseWebFunction {

    @Autowired
    IService service;

}

package com.jason.jlh.common.controller;

import com.jason.jlh.common.service.IBaseService;

/**
 * @title: BaseController
 * @package: com.jason.jlh.common.controller
 * @description: 控制器基类
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseController<Service extends IBaseService> extends AbstractCommonController<Service> {
}

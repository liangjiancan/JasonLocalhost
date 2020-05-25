package com.jason.jlh.common.service;

import com.jason.jlh.common.pojo.BaseDTO;
import com.jason.jlh.common.pojo.BaseEntity;
import com.jason.jlh.common.support.BaseServiceFunction;
import com.jason.jlh.common.support.BaseValidateFunction;

/**
 * @title: BaseService
 * @package: com.jason.jlh.common.service
 * @description: 服务实现类基类接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseService<DTO extends BaseDTO, Entity extends BaseEntity>
        extends AbstractConverterService<DTO, Entity>
        implements BaseServiceFunction, BaseValidateFunction {

}

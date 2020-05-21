package com.jason.jlh.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.jlh.common.pojo.BaseDTO;
import com.jason.jlh.common.pojo.BaseEntity;

/**
 * @title: BaseService
 * @package: com.jason.jlh.common.service
 * @description: 服务实现类基类接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseService<DTO extends BaseDTO, Entity extends BaseEntity, Mapper extends BaseMapper>
        extends AbstractCrudService<DTO, Entity, Mapper> {

}

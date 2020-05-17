package com.jason.jlh.common.service;

import com.jason.jlh.common.exception.ServiceException;
import com.jason.jlh.common.pojo.BaseDTO;
import com.jason.jlh.common.pojo.BaseEntity;
import com.jason.jlh.common.support.BaseServiceFunction;
import com.jason.jlh.common.support.Converter;
import com.jason.jlh.common.support.IConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

/**
 * @title: AbstractConverterService
 * @package: com.jason.jlh.common.service
 * @description: 对象转换服务抽象类
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
@Slf4j
@SuppressWarnings("unchecked")
public abstract class AbstractConverterService<Dto extends BaseDTO, Entity extends BaseEntity>
        implements BaseService, BaseServiceFunction, IConverter<Dto, Entity> {

    /**
     * Service上主要的Dto和Entity转换工具
     */
    @Getter
    protected Converter<Dto, Entity> converter = null;

    private Class<Dto> clazzDto = null;
    private Class<Entity> clazzEntity = null;

    public AbstractConverterService() {
        try {
            ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
            this.clazzDto = (Class) pt.getActualTypeArguments()[0];
            this.clazzEntity = (Class) pt.getActualTypeArguments()[1];
            if (clazzDto != null && clazzEntity != null) {
                converter = new Converter<>(clazzDto, clazzEntity);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 创建DTO对象
     *
     * @param: []
     * @return: Dto
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public Dto createDto() {
        if (converter == null) {
            throw buildServiceExceptionForParamNotSet();
        }
        return converter.createDto();
    }

    /**
     * 创建Entity对象
     *
     * @param: []
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public Entity createEntity() {
        if (converter == null) {
            throw buildServiceExceptionForParamNotSet();
        }
        return converter.createEntity();
    }

    /**
     * 构造一个Dto/Entity转换异常的ServiceException
     *
     * @param: []
     * @return: com.jason.jlh.common.exception.ServiceException
     * @author:
     * @date: 2020/5/3
     */
    private ServiceException buildServiceExceptionForParamNotSet() {
        return new ServiceException(this.getClass().getName() + "'s <Dto, Entity> not set.");
    }

}

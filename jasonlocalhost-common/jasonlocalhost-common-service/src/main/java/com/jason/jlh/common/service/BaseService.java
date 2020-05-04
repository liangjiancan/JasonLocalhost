package com.jason.jlh.common.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.jason.jlh.common.converter.Converter;
import com.jason.jlh.common.converter.IConverter;
import com.jason.jlh.common.enums.YesornoEnum;
import com.jason.jlh.common.exception.ServiceException;
import com.jason.jlh.common.pojo.BaseDTO;
import com.jason.jlh.common.pojo.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;

import java.lang.reflect.ParameterizedType;

/**
 * @title: BaseService
 * @package: com.jason.jlh.common.service
 * @description: 服务实现类基类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Slf4j
@SuppressWarnings("unchecked")
public abstract class BaseService<Dto extends BaseDTO, Entity extends BaseEntity>
        implements IConverter<Dto, Entity>, IBaseFunction {

    /**
     * 常用常量
     */
    protected static final YesornoEnum Yes = YesornoEnum.Yes;
    protected static final YesornoEnum No = YesornoEnum.No;

    /**
     * Service上主要的Dto和Entity转换工具
     */
    protected Converter<Dto, Entity> converter = null;

    private Class<Dto> clazzDto = null;
    private Class<Entity> clazzEntity = null;

    public BaseService() {
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
     * 获取转换者对象
     *
     * @param: []
     * @return: com.jason.jlh.common.converter.Converter
     * @author:
     * @date: 2020/5/3
     */
    public Converter<Dto, Entity> getConverter() {
        return converter;
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
    protected static <T> T that() {
        return (T) AopContext.currentProxy();
    }
}

package com.jason.jlh.common.support;

import com.jason.jlh.common.pojo.BaseValueObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @title: Converter
 * @package: com.jason.jlh.common.support
 * @description: Dto和Entity转换类接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Slf4j
public class Converter<Dto extends BaseValueObject, Entity extends BaseValueObject> implements IConverter<Dto, Entity> {

    private Class<Dto> clazzDto = null;
    private Class<Entity> clazzEntity = null;

    public Converter(Class<Dto> clazzDto, Class<Entity> clazzEntity) {
        this.clazzDto = clazzDto;
        this.clazzEntity = clazzEntity;
    }

    /**
     * 创建DTO
     *
     * @param: []
     * @return: Dto
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public Dto createDto() {
        try {
            return clazzDto.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 创建Entity
     *
     * @param: []
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public Entity createEntity() {
        try {
            return clazzEntity.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

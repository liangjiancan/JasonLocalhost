package com.jason.jlh.common.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.jlh.common.pojo.BaseValueObject;
import com.jason.jlh.common.pojo.query.Pages;
import com.jason.jlh.common.pojo.query.QueryDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @title: IConverter
 * @package: com.jason.jlh.common.service
 * @description: Dto和Entity转换类接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IConverter<Dto extends BaseValueObject, Entity extends BaseValueObject> {

    /**
     * 创建一个DTO对象
     *
     * @param: []
     * @return: Dto
     * @author:
     * @date: 2020/5/3
     */
    Dto createDto();

    /**
     * 创建一个Entity对象
     *
     * @param: []
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    Entity createEntity();

    /**
     * Entity转DTO
     *
     * @param: [pojo]
     * @return: Dto
     * @author:
     * @date: 2020/5/3
     */
    default Dto toDto(Entity entity) {
        if (entity == null) {
            return null;
        }
        Dto dto = createDto();
        copyProperties(entity, dto);
        return dto;
    }

    /**
     * Entity列表转DTO列表
     *
     * @param: [entities]
     * @return: java.util.List<Dto>
     * @author:
     * @date: 2020/5/3
     */
    default List<Dto> toDtoList(List entities) {
        List<Dto> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entities)) {
            for (Object entity : entities) {
                result.add(toDto((Entity) entity));
            }
        }
        return result;
    }

    /**
     * DTO转Entity
     *
     * @param: [pojo]
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    default Entity toEntity(Dto dto) {
        if (dto == null) {
            return null;
        }
        Entity entity = createEntity();
        copyProperties(dto, entity);
        return entity;
    }

    /**
     * DTO转Entity, 转换指定的参数
     *
     * @param: [pojo, props]
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    default Entity toEntityIncluding(Dto dto, String... props) {
        if (dto == null) {
            return null;
        }
        Entity entity = createEntity();
        copyPropertiesIncluding(dto, entity, props);
        return entity;
    }

    /**
     * DTO转Entity, 排除指定的参数
     *
     * @param: [pojo, props]
     * @return: Entity
     * @author:
     * @date: 2020/5/3
     */
    default Entity toEntityExcluding(Dto dto, String... props) {
        if (dto == null) {
            return null;
        }
        Entity entity = createEntity();
        copyPropertiesExcluding(dto, entity, props);
        return entity;
    }

    /**
     * DTO列表转Entity列表
     *
     * @param: [dtos]
     * @return: java.util.List<Entity>
     * @author:
     * @date: 2020/5/3
     */
    default List<Entity> toEntityList(List dtos) {
        List<Entity> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtos)) {
            for (Object dto : dtos) {
                result.add(toEntity((Dto) dto));
            }
        }
        return result;
    }

    /**
     * DTO列表转Entity列表，排除指定的参数
     *
     * @param: [dtos, props]
     * @return: java.util.List<Entity>
     * @author:
     * @date: 2020/5/3
     */
    default List<Entity> toEntityListExcluding(List dtos, String... props) {
        List<Entity> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtos)) {
            for (Object dto : dtos) {
                result.add(toEntityExcluding((Dto) dto, props));
            }
        }
        return result;
    }

    /**
     * DTO列表转Entity列表, 转换指定的参数
     *
     * @param: [dtos, props]
     * @return: java.util.List<Entity>
     * @author:
     * @date: 2020/5/3
     */
    default List<Entity> toEntityListIncluding(List dtos, String... props) {
        List<Entity> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtos)) {
            for (Object dto : dtos) {
                result.add(toEntityIncluding((Dto) dto, props));
            }
        }
        return result;
    }

    /**
     * 复制属性值
     *
     * @param: [src, desc]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyProperties(Object src, Object desc) {
        BeanUtils.copyProperties(src, desc);
    }

    /**
     * 复制属性值时只复制非空的属性
     *
     * @param: [src, trg]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyPropertiesForNotNullVal(Object src, Object trg) {
        PropertyDescriptor srcProp;
        Object value;
        Method writeMethod;
        for (PropertyDescriptor p : BeanUtils.getPropertyDescriptors(trg.getClass())) {
            try {
                srcProp = BeanUtils.getPropertyDescriptor(src.getClass(), p.getName());
                if (srcProp == null) {
                    continue;
                }
                value = srcProp.getReadMethod().invoke(src);
                if (value != null) {
                    writeMethod = p.getWriteMethod();
                    if (writeMethod != null) {
                        writeMethod.invoke(trg, value);
                    }
                }
            } catch (Throwable ex) {
                throw new FatalBeanException(
                        "Could not copy property '" + p.getName() + "' from source to target", ex);
            }
        }
    }

    /**
     * 复制属性值时只复制指定属性
     *
     * @param: [src, trg, props]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyPropertiesIncluding(Object src, Object trg, String... props) {
        copyPropertiesIncluding(src, trg, Arrays.asList(props));
    }

    /**
     * 复制属性值时只复制指定属性
     *
     * @param: [src, trg, props]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyPropertiesIncluding(Object src, Object trg, Collection<String> props) {
        //以下方法可以使用BeanUtils的缓存，性能更高，
        PropertyDescriptor srcProp;
        Object value;
        Method writeMethod;
        for (PropertyDescriptor p : BeanUtils.getPropertyDescriptors(trg.getClass())) {
            try {
                if (props.contains(p.getName())) {
                    srcProp = BeanUtils.getPropertyDescriptor(src.getClass(), p.getName());
                    if (srcProp == null) {
                        continue;
                    }
                    value = srcProp.getReadMethod().invoke(src);
                    writeMethod = p.getWriteMethod();
                    if (writeMethod != null) {
                        writeMethod.invoke(trg, value);
                    }
                }
            } catch (Throwable ex) {
                throw new FatalBeanException(
                        "Could not copy property '" + p.getName() + "' from source to target", ex);
            }
        }
    }

    /**
     * 复制属性值时排除指定属性
     *
     * @param: [src, trg, props]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyPropertiesExcluding(Object src, Object trg, String... props) {
        BeanUtils.copyProperties(src, trg, props);
    }

    /**
     * 复制属性值时排除指定属性
     *
     * @param: [src, trg, props]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    default void copyPropertiesExcluding(Object src, Object trg, Collection<String> props) {
        String[] ignores = new String[props.size()];
        props.toArray(ignores);
        BeanUtils.copyProperties(src, trg, ignores);
    }

    /**
     * 封装分页结果为DTO的分页结果
     *
     * @param: [page, ipage]
     * @return: com.jason.jlh.common.pojo.query.Pages<Dto>
     * @author: 
     * @date: 2020/5/3
     */
    default Pages<Dto> getDtoPage(Pages<Dto> page, IPage ipage) {
        page.setTotals(ipage.getTotal());
        page.setRecords(toDtoList(ipage.getRecords()));
        return page;
    }

    /**
     * 封装分页结果为DTO的分页结果
     *
     * @param: [query, ipage]
     * @return: com.jason.jlh.common.pojo.query.Pages<Dto>
     * @author: 
     * @date: 2020/5/3
     */
    default Pages<Dto> getDtoPage(QueryDTO query, IPage ipage) {
        return getDtoPage(query.getPage(), ipage);
    }

    /**
     * 获取分页对象
     *
     * @param: [query]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<Entity>
     * @author: 
     * @date: 2020/5/3
     */
    default IPage<Entity> getEntityPage(QueryDTO query) {
        return getEntityPage(query);
    }

    /**
     * 获取分页对象
     *
     * @param page
     * @return
     */
    default IPage getEntityPage(Pages page) {
        return new Page(page.getPage(), page.getPageSize());
    }
}

package com.jason.jlh.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.jlh.common.pojo.BaseDTO;
import com.jason.jlh.common.pojo.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @title: AbstractCrudService
 * @package: com.jason.jlh.common.service
 * @description: CURD服务抽象类
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
@Deprecated
@SuppressWarnings("unchecked")
public abstract class AbstractCrudService<Dto extends BaseDTO, Entity extends BaseEntity, Mapper extends BaseMapper>
        extends AbstractConverterService<Dto, Entity, Mapper>
        implements ICrudService<Dto> {

    @Autowired
    protected Mapper mapper;

    /**
     * 根据主键查询
     *
     * @param: [id]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    @Override
    public Dto selectById(@NotBlank(message = "操作失败, 主键不能为空") String id) {
        return toDto((Entity) mapper.selectById(id));
    }

    /**
     * 新增
     *
     * @param: [dto]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    @Override
    public Dto insert(@NotNull(message = "操作失败, 参数不能为空") Dto dto) {
        Entity entity = toEntity(dto);
        boolean success = retBool(mapper.insert(entity));
        checkArgument(success, "操作失败");
        return toDto(entity);
    }

    /**
     * 根据主键更新
     *
     * @param: [dto]
     * @return: Dto
     * @author: huyongjun
     * @date: 2020/5/14
     */
    @Override
    public Dto updateById(@NotNull(message = "操作失败, 参数不能为空") Dto dto) {
        Entity entity = toEntity(dto);
        boolean success = retBool(mapper.updateById(entity));
        checkArgument(success, "操作失败");
        return toDto(entity);
    }

    /**
     * 根据主键删除
     *
     * @param: [id]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/14
     */
    @Override
    public boolean deleteById(@NotBlank(message = "操作失败, 主键不能为空") String id) {
        boolean success = retBool(mapper.deleteById(id));
        checkArgument(success, "操作失败");
        return true;
    }

}

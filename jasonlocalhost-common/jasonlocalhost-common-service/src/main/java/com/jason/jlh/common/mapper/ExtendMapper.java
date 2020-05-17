package com.jason.jlh.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
 * @title: ExtendMapper
 * @package: com.jason.jlh.common.mapper
 * @description: 基于MyBatis-plus的Mapper基类的增强Mapper, 提供额外的方法
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public interface ExtendMapper<Entity> extends BaseMapper<Entity> {

    /**
     * 全字段更新, 包括空值的字段
     * 由于BaseMapper的updateById方法不更新空值的字段, 所以有必要提供此方法
     *
     * @param: [entity]
     * @return: int
     * @author: huyongjun
     * @date: 2020/5/16
     */
    int updateAllColumnById(@Param(Constants.ENTITY) Entity entity);
}

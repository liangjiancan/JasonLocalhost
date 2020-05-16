package com.jason.jlh.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

/**
 * @title: BaseValueObject
 * @package: com.jason.jlh.common.pojo
 * @description: 实体类基类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public abstract class BaseEntity extends BaseValueObject {

    private static final long serialVersionUID = 1L;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;

    /**
     * 修改人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedById;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
}

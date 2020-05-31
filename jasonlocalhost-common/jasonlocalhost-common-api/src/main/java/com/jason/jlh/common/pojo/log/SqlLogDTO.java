package com.jason.jlh.common.pojo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title: SqlLogDTO
 * @package: com.jason.jlh.common.pojo.log
 * @description: SQL日志DTO
 * @author:
 * @date: 2020/5/24
 * @version: v1.0
 */
@ApiModel(value = "SqlLogDTO对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class SqlLogDTO extends LogDTO {

    private static final long serialVersionUID = 6352721701521540390L;

    /**
     * SQL语句
     */
    @ApiModelProperty(value = "SQL语句")
    private String sql;

    /**
     * SQL参数
     */
    @ApiModelProperty(value = "SQL参数")
    private String params;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private long costTime;

    /**
     * SQL执行结果
     */
    @ApiModelProperty(value = "SQL执行结果")
    private Integer result;

    /**
     * SQL类别
     */
    @ApiModelProperty(value = "SQL类别")
    private String commandType;

    /**
     * 类名和方法名
     */
    @ApiModelProperty(value = "类名和方法名")
    private String methodName;

}

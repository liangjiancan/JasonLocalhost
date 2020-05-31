package com.jason.jlh.common.pojo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title: BusinessLogDTO
 * @package: com.jason.jlh.common.pojo.log
 * @description: 业务日志DTO
 * @author:
 * @date: 2020/5/24
 * @version: v1.0
 */
@ApiModel(value = "BusinessLogDTO对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessLogDTO extends LogDTO {

    /**
     * 类名和方法名
     */
    @ApiModelProperty(value = "类名和方法名")
    private String methodName;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private long costTime;

    /**
     * SQL参数
     */
    @ApiModelProperty(value = "SQL参数")
    private String params;

    /**
     * SQL执行结果
     */
    @ApiModelProperty(value = "SQL执行结果")
    private String result;

    /**
     * 异常
     */
    @ApiModelProperty(value = "异常")
    private String exception;

}

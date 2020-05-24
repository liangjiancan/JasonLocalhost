package com.jason.jlh.common.pojo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @title: LogDTO
 * @package: com.jason.jlh.common.pojo.log
 * @description: 日志DTO
 * @author: huyongjun
 * @date: 2020/5/24
 * @version: v1.0
 */
@ApiModel(value = "LogDTO对象")
@Data
@EqualsAndHashCode
public class LogDTO {

    private static final long serialVersionUID = 8317340479561418685L;

    /**
     * 操作者
     */
    @ApiModelProperty(value = "操作者")
    private String operateBy;

    /**
     * 操作者ID
     */
    @ApiModelProperty(value = "操作者ID")
    private String operateById;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operateTime;
}
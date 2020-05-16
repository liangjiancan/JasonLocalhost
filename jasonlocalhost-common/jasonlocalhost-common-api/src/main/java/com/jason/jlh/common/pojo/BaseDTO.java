package com.jason.jlh.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.groups.Default;
import java.time.LocalDateTime;

/**
 * @title: BaseValueObject
 * @package: com.jason.jlh.common.pojo
 * @description: DTO基类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public abstract class BaseDTO extends BaseValueObject {

    private static final long serialVersionUID = 1L;

    /**
     * 创建场景
     */
    public interface Insert extends Default {
    }

    /**
     * 更新场景
     */
    public interface Update extends Default {
    }

     /**
      * 修改人
      */
     @ApiModelProperty(value = "修改人")
     private String modifiedBy;

     /**
      * 修改人ID
      */
     @ApiModelProperty(value = "修改人ID")
     private String modifiedById;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;
}

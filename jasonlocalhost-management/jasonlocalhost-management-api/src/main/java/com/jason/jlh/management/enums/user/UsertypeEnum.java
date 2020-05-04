package com.jason.jlh.management.enums.user;

import com.jason.jlh.common.enums.IComparableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @title: UsertypeEnum
 * @package: com.jason.jlh.management.enums.user
 * @description: 用户类型枚举
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@ApiModel("management.usertype 用户类型枚举")
@Getter
public enum UsertypeEnum implements IComparableEnum<Integer> {

    /**
     * 访客
     */
    @ApiModelProperty("访客")
    GUEST(0),

    /**
     * 未激活用户
     */
    @ApiModelProperty("未激活用户")
    NOT_ACTIVATE(1),

    /**
     * 已激活用户
     */
    @ApiModelProperty("已激活用户")
    ACTIVATED(2),

    /**
     * 超级管理员
     */
    @ApiModelProperty("超级管理员")
    ADMIN(9);

    private Integer value;

    UsertypeEnum(Integer value) {
        this.value = value;
    }

}

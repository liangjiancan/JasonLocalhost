package com.jason.jlh.management.enums.user;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jason.jlh.common.enums.IComparableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @title: UsertypeEnum
 * @package: com.jason.jlh.management.enums.user
 * @description: 用户类型枚举类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Getter
@ToString
@AllArgsConstructor
@ApiModel("management.usertype 用户类型枚举类")
public enum UsertypeEnum implements IComparableEnum<Integer> {

    /**
     * 超级管理员
     */
    @ApiModelProperty("超级管理员")
    ADMIN(0, "超级管理员"),

    /**
     * 普通用户
     */
    @ApiModelProperty("普通用户")
    USER(1, "普通用户"),

    /**
     * 未激活用户
     */
    @ApiModelProperty("未激活用户")
    NOT_ACTIVATE(2, "未激活用户"),

    /**
     * 访客
     */
    @ApiModelProperty("访客")
    GUEST(9, "访客");

    private Integer value;
    @JsonValue
    private String desc;

    public static String getDescByValue(Integer value) {
        for (UsertypeEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item.getDesc();
            }
        }
        return null;
    }
}

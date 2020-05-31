package com.jason.jlh.management.enums.user;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jason.jlh.common.enums.IComparableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @title: SexEnum
 * @package: com.jason.jlh.management.enums.user
 * @description: 性别枚举类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Getter
@ToString
@AllArgsConstructor
@ApiModel("management.sex 性别枚举类")
public enum SexEnum implements IComparableEnum<Integer> {

    /**
     * 女
     */
    @ApiModelProperty("女")
    ADMIN(0, "女"),

    /**
     * 男
     */
    @ApiModelProperty("男")
    USER(1, "男");

    private Integer value;
    @JsonValue
    private String desc;

    public static String getDescByValue(Integer value) {
        for (SexEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item.getDesc();
            }
        }
        return null;
    }
}

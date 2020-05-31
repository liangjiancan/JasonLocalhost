package com.jason.jlh.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @title: YesornoEnum
 * @package: com.jason.jlh.common.enums
 * @description: 是否枚举类
 * @author:
 * @date: 2020/5/16
 * @version: v1.0
 */
@Getter
@ToString
@AllArgsConstructor
@ApiModel("global.yesorno(是否)枚举类")
public enum YesornoEnum implements IComparableEnum<Integer> {

    /**
     * 否
     */
    @ApiModelProperty("否")
    No(0, "否"),

    /**
     * 是
     */
    @ApiModelProperty("是")
    Yes(1, "是");

    private Integer value;

    @JsonValue
    private String desc;

    public static String getDescByValue(Integer value) {
        for (YesornoEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item.getDesc();
            }
        }
        return null;
    }
}
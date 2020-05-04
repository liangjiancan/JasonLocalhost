package com.jason.jlh.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 由资源global.yesorno(是否)生成的枚举类
 *
 * @param:
 * @return:
 * @author: huyongjun
 * @date: 2020/5/3
 */
@Getter
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
    private String desc;

    YesornoEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getDescByValue(Integer value) {
        YesornoEnum[] yesornoEnums = values();
        for (YesornoEnum yesornoEnum : yesornoEnums) {
            if (yesornoEnum.getValue().equals(value)) {
                return yesornoEnum.getDesc();
            }
        }
        return null;
    }
}
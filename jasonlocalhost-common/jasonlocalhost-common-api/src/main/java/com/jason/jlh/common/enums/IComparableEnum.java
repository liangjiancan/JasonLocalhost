package com.jason.jlh.common.enums;

import java.io.Serializable;

/**
 * @title: IComparableEnum
 * @package: com.jason.jlh.common.enums
 * @description: 可比较判断的枚举接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IComparableEnum<T extends Serializable> extends IBaseEnum<T> {

    /**
     * 判断枚举值是否一致
     *
     * @param: [value]
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    default boolean compare(T value) {
        T mine = getValue();
        return mine.equals(value);
    }

}

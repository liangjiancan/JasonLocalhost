package com.jason.jlh.common.enums;

import java.io.Serializable;

/**
 * @title: IBaseEnum
 * @package: com.jason.jlh.common.enums
 * @description: 枚举基类接口
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public interface IBaseEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();

}

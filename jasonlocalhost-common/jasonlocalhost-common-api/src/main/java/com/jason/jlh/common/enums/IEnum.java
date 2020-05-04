package com.jason.jlh.common.enums;

import java.io.Serializable;

/**
 * 自定义枚举接口
 *
 * @param:
 * @return:
 * @author: huyongjun
 * @date: 2020/5/3
 */
public interface IEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();

}

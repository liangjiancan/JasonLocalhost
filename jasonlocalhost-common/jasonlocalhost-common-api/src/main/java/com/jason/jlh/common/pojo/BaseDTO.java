package com.jason.jlh.common.pojo;

import javax.validation.groups.Default;

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

}

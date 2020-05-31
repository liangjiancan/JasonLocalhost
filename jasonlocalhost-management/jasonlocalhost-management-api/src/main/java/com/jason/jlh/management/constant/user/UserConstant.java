package com.jason.jlh.management.constant.user;

import com.jason.jlh.common.constant.CommonTableColumnConstant;

/**
 * @title: UserConstant
 * @package: com.jason.jlh.management.constant.user
 * @description: 用户信息常量类
 * @author:
 * @date: 2020/5/15
 * @version: v1.0
 */
public class UserConstant implements CommonTableColumnConstant {

    private UserConstant() {
    }

    /**
     * tb_user表字段
     */
    public static final String TABLE_COLUMN_USER_NAME = "user_name";
    public static final String TABLE_COLUMN_EMAIL = "email";
    public static final String TABLE_COLUMN_NICK_NAME = "nick_name";

}

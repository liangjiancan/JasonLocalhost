package com.jason.jlh.management.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import com.jason.jlh.common.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @title: User
 * @package: com.jason.jlh.management
 * @description: 用户信息实体类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
@ApiModel(value = "User对象", description = "tb_user 用户信息表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 661438671908597303L;

    /**
     * 用户主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 是否已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 版本号
     */
    @Version
    private Integer version;

}

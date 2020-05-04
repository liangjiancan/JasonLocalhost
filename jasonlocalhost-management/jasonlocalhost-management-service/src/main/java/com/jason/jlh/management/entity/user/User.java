package com.jason.jlh.management.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jason.jlh.common.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
@ApiModel(value="User对象", description="tb_user 用户信息表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 661438671908597303L;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;
}

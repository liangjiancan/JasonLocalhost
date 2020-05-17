package com.jason.jlh.management.dto.user;

import com.jason.jlh.common.pojo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @title: UserDTO
 * @package: com.jason.jlh.management.pojo
 * @description: 用户信息DTO
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@ApiModel(value = "UserDTO对象", description = "tb_user 用户表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

    private static final long serialVersionUID = 665633605194309643L;

    public interface Register {
    }

    /**
     * 用户主键
     */
    @NotBlank(groups = {Update.class}, message = "用户ID不能为空")
    @ApiModelProperty(value = "用户主键")
    private String id;

    /**
     * 用户名
     */
    @NotBlank(groups = {Register.class}, message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 邮箱
     */
    @NotBlank(groups = {Register.class}, message = "邮箱不能为空")
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
    @NotNull(groups = {Register.class}, message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    private Integer deleted;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;
}

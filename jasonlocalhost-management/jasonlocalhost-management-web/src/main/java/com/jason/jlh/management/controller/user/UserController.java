package com.jason.jlh.management.controller.user;

import com.jason.jlh.common.annotation.BusinessController;
import com.jason.jlh.common.controller.BaseController;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;

/**
 * @title: UserController
 * @package: com.jason.jlh.management.controller.user
 * @description: 用户信息控制器
 * @author:
 * @date: 2020/5/4
 * @version: v1.0
 */
@Api("用户信息")
@BusinessController("/user")
public class UserController extends BaseController {

    @Reference
    private IUserService service;

    /**
     * 根据主键查看用户数据
     *
     * @param: [id]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author:
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键查看用户数据")
    @GetMapping("/select")
    public UserDTO select(@ApiParam("用户主键") @NotBlank(message = "主键不能为空") String id) {
        return service.selectById(id);
    }

    /**
     * 根据主键修改用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author:
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键修改用户数据")
    @PostMapping("/register")
    public UserDTO register(@RequestBody @Validated({UserDTO.Register.class}) UserDTO userDTO) {
        return service.register(userDTO);
    }

    /**
     * 根据主键修改用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author:
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键修改用户数据")
    @PostMapping("/update")
    public UserDTO update(@RequestBody @Validated({UserDTO.Update.class}) UserDTO userDTO) {
        return service.update(userDTO);
    }

    /**
     * 注销账号
     *
     * @param: [id]
     * @return: boolean
     * @author:
     * @date: 2020/5/15
     */
    @ApiOperation("注销账号")
    @PostMapping("/unsubscribe")
    public boolean unsubscribe(@ApiParam("用户主键") @NotBlank(message = "主键不能为空") String id) {
        return service.deleteById(id);
    }
}

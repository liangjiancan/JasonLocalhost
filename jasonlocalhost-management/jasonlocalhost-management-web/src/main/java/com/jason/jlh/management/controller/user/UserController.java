package com.jason.jlh.management.controller.user;

import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @title: UserController
 * @package: com.jason.jlh.management.controller.user
 * @description: 用户信息控制器
 * @author: huyongjun
 * @date: 2020/5/4
 * @version: v1.0
 */
@Api("用户信息")
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Reference(loadbalance = "roundrobin")
    private IUserService userService;

    /**
     * 根据主键查看用户数据
     *
     * @param: [id]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键查看用户数据")
    @GetMapping("/select")
    public UserDTO select(@ApiParam("用户主键") @NotBlank(message = "主键不能为空") String id) {
        return userService.selectById(id);
    }

    /**
     * 根据主键修改用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键修改用户数据")
    @PostMapping("/register")
    public UserDTO register(@RequestBody @Validated({UserDTO.Register.class}) UserDTO userDTO) {
        return userService.register(userDTO);
    }

    /**
     * 根据主键修改用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @ApiOperation("根据主键修改用户数据")
    @PostMapping("/update")
    public UserDTO update(@RequestBody @Validated({UserDTO.Update.class}) UserDTO userDTO) {
        return userService.update(userDTO);
    }

    /**
     * 注销账号
     *
     * @param: [id]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @ApiOperation("注销账号")
    @PostMapping("/unsubscribe")
    public boolean unsubscribe(@ApiParam("用户主键") @NotBlank(message = "主键不能为空") String id) {
        return userService.deleteById(id);
    }
}

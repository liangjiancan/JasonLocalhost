package com.jason.jlh.management.controller.user;

import com.jason.jlh.management.service.impl.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: UserController
 * @package: com.jason.jlh.management.controller.user
 * @description: 用户信息Controller
 * @author: huyongjun
 * @date: 2020/5/4
 * @version: v1.0
 */
@RestController("user")
public class UserController {

    @Autowired
    IUserService userService;

}

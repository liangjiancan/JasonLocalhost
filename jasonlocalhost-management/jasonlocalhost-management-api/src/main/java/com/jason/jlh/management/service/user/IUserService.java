package com.jason.jlh.management.service.user;

import com.jason.jlh.common.service.ICrudService;
import com.jason.jlh.management.dto.user.UserDTO;

/**
 * @title: IUserService
 * @package: com.jason.jlh.management.service.user
 * @description: 用户信息服务接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IUserService extends ICrudService<UserDTO> {

    /**
     * 更新用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author:
     * @date: 2020/5/15
     */
    UserDTO update(UserDTO userDTO);

    /**
     * 注册
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author:
     * @date: 2020/5/17
     */
    UserDTO register(UserDTO userDTO);

}

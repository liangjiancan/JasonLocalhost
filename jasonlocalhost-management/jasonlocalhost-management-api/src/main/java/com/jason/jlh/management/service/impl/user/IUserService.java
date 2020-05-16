package com.jason.jlh.management.service.impl.user;

import com.jason.jlh.common.service.ICrudService;
import com.jason.jlh.management.dto.user.UserDTO;

import javax.validation.constraints.NotBlank;

/**
 * @title: IUserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务接口
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IUserService extends ICrudService<UserDTO> {

    /**
     * 更新用户数据
     * 
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    UserDTO update(UserDTO userDTO);
}

package com.jason.jlh.management.service.impl.user;

import com.jason.jlh.common.service.BaseService;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.entity.user.User;

/**
 * @title: UserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务实现类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public class UserService extends BaseService<UserDTO, User> implements IUserService {

    /**
     * 根据主键获取用户信息
     *
     * @param id
     * @param: [id]
     * @return: com.jason.jlh.management.pojo.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/3
     */
    @Override
    public UserDTO queryById(String id) {
        return null;
    }
}

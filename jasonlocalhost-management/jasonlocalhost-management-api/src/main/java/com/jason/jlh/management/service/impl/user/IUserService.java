package com.jason.jlh.management.service.impl.user;

import com.jason.jlh.common.service.IBaseService;
import com.jason.jlh.management.dto.user.UserDTO;

/**
 * @title: IUserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务接口
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IUserService extends IBaseService {

    /**
     * 根据主键获取用户信息
     * 
     * @param: [id]
     * @return: com.jason.jlh.management.pojo.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/3
     */
    UserDTO queryById(String id);
}

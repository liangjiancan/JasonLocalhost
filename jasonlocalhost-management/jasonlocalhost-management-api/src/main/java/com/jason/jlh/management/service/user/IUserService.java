package com.jason.jlh.management.service.user;

import com.jason.jlh.common.service.IBaseService;
import com.jason.jlh.management.dto.user.UserDTO;

/**
 * @title: IUserService
 * @package: com.jason.jlh.management.service.user
 * @description: 用户信息服务接口
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IUserService extends IBaseService {

    /**
     * 根据主键查询
     *
     * @param: [id]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/18
     */
    UserDTO selectById(String id);

    /**
     * 更新用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    UserDTO update(UserDTO userDTO);

    /**
     * 根据主键逻辑删除
     *
     * @param: [id]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/18
     */
    boolean deleteById(String id);

    /**
     * 注册
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/17
     */
    UserDTO register(UserDTO userDTO);

}

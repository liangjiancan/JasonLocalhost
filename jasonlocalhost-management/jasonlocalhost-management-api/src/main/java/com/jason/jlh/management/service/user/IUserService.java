package com.jason.jlh.management.service.user;

import com.jason.jlh.common.service.IBaseService;
import com.jason.jlh.management.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title: IUserService
 * @package: com.jason.jlh.management.service.user
 * @description: 用户信息服务接口
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@FeignClient("JASONLOCALHOST-MANAGEMENT-SERVICE")
public interface IUserService extends IBaseService {

    /**
     * 根据主键查询
     *
     * @param: [id]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/18
     */
    @PostMapping(value = "/selectById")
    @ResponseBody
    UserDTO selectById(@RequestBody String id);

    /**
     * 更新用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @PostMapping("/update")
    @ResponseBody
    UserDTO update(@RequestBody UserDTO userDTO);

    /**
     * 根据主键逻辑删除
     *
     * @param: [id]
     * @return: boolean
     * @author: huyongjun
     * @date: 2020/5/18
     */
    @PostMapping(value = "/deleteById")
    boolean deleteById(@RequestBody String id);

    /**
     * 注册
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/17
     */
    @PostMapping("/register")
    @ResponseBody
    UserDTO register(@RequestBody UserDTO userDTO);

}

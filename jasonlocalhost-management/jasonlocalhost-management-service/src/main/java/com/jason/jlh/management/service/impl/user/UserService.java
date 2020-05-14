package com.jason.jlh.management.service.impl.user;

import com.jason.jlh.common.service.AbstractMapperService;
import com.jason.jlh.management.dao.user.UserMapper;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.entity.user.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

/**
 * @title: UserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务实现类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@Component
public class UserService extends AbstractMapperService<UserDTO, User, UserMapper> implements IUserService {

}

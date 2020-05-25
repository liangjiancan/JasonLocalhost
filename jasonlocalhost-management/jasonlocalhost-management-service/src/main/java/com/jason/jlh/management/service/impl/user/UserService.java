package com.jason.jlh.management.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.jason.jlh.common.service.AbstractConverterService;
import com.jason.jlh.common.service.BaseCrudService;
import com.jason.jlh.common.service.BaseService;
import com.jason.jlh.management.constant.user.UserConstant;
import com.jason.jlh.management.dao.user.UserMapper;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.entity.user.User;
import com.jason.jlh.management.enums.user.UsertypeEnum;
import com.jason.jlh.management.service.user.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.Arrays;

/**
 * @title: UserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务实现类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@Service(loadbalance = "roundrobin")
public class UserService extends BaseCrudService<UserDTO, User, UserMapper> implements IUserService {

    /**
     * 更新用户数据
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/15
     */
    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = mapper.selectById(userDTO.getId());
        checkNotNull(user, "操作失败, 当前用户不存在");
        String nickName = Strings.emptyToNull(userDTO.getNickName());
        if (null != nickName && !StringUtils.equals(user.getNickName(), nickName)) {
            // 昵称唯一性校验
            checkUnique(UserConstant.TABLE_COLUMN_NICK_NAME, nickName);
        }
        copyPropertiesIncluding(userDTO, user, Arrays.asList("nickName", "sex"));
        boolean success = retBool(mapper.updateAllColumnById(user));
        checkArgument(success, "操作失败");
        return toDto(user);
    }

    /**
     * 注册
     *
     * @param: [userDTO]
     * @return: com.jason.jlh.management.dto.user.UserDTO
     * @author: huyongjun
     * @date: 2020/5/17
     */
    @Override
    public UserDTO register(UserDTO userDTO) {
        // 用户名唯一性校验
        checkUnique(UserConstant.TABLE_COLUMN_USER_NAME, userDTO.getUserName());
        // 邮箱唯一性校验
        checkUnique(UserConstant.TABLE_COLUMN_EMAIL, userDTO.getEmail());

        String nickName = userDTO.getNickName();
        Strings.emptyToNull(nickName);
        if (null != nickName) {
            // 昵称唯一性校验
            checkUnique(UserConstant.TABLE_COLUMN_NICK_NAME, nickName);
        }

        User user = new User();
        copyPropertiesIncluding(userDTO, user, Arrays.asList("userName", "password", "email", "nickName", "sex"));
        user.setUserType(UsertypeEnum.NOT_ACTIVATE.getValue());
        boolean success = retBool(mapper.insert(user));
        checkArgument(success, "操作失败");
        return toDto(user);
    }

    /**
     * 唯一性校验
     *
     * @param: [tableColumn, value]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/18
     */
    private void checkUnique(String tableColumn, String value) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(tableColumn, value)
                .eq(UserConstant.TABLE_COLUMN_DELETED, No.getValue());
        boolean exists = mapper.selectCount(queryWrapper) > 0;
        checkArgument(!exists, "操作失败, 校验不通过");
    }
}

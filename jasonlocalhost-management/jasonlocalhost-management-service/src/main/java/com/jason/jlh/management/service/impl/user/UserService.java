package com.jason.jlh.management.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.jason.jlh.common.service.AbstractCrudService;
import com.jason.jlh.common.utils.PreconditionUtil;
import com.jason.jlh.management.constant.user.UserConstant;
import com.jason.jlh.management.dao.user.UserMapper;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @title: UserService
 * @package: com.jason.jlh.management.service.impl.user
 * @description: 用户信息服务实现类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@Component
public class UserService extends AbstractCrudService<UserDTO, User, UserMapper> implements IUserService {

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
        PreconditionUtil.checkNotNull(user, "操作失败, 当前用户不存在");
        String nickName = Strings.emptyToNull(userDTO.getNickName());
        if (!StringUtils.equals(user.getNickName(), nickName)) {
            // 昵称唯一性校验
            checkNickName(nickName);
        }
        copyPropertiesIncluding(userDTO, user, Arrays.asList("nickName", "sex"));
        boolean success = retBool(mapper.updateAllColumnById(user));
        PreconditionUtil.checkArgument(success, "操作失败");
        return toDto(user);
    }

    /**
     * 昵称唯一性校验
     *
     * @param: [nickName]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/15
     */
    private void checkNickName(String nickName) {
        if (null == nickName) {
            return;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserConstant.TABLE_COLUMN_NICK_NAME, nickName);
        boolean exists = mapper.selectCount(queryWrapper) > 0;
        PreconditionUtil.checkArgument(!exists, "操作失败, 该昵称已被使用");
    }
}

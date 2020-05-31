package com.jason.jlh.common.pojo;

import com.jason.jlh.common.utils.SpringContextUtil;
import com.jason.jlh.management.enums.user.UsertypeEnum;
import com.jason.jlh.management.dto.user.UserDTO;
import com.jason.jlh.management.service.user.IUserService;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @title: GlobalParam
 * @package: com.jason.jlh.common.pojo
 * @description: 全局参数实现类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Data
public class GlobalParam implements IGlobalParam {

    /**
     * 线程安全对象
     */
    private static final ThreadLocal<GlobalParam> LOCAL = ThreadLocal.withInitial(() -> new GlobalParam());

    /**
     * 服务类名
     */
    private String className;

    /**
     * 服务方法名
     */
    private String methodName;

    /**
     * 操作用户ID
     */
    private String userId;

    /**
     * 操作用户名
     */
    private String userName;

    /**
     * 操作用户会话
     */
    private String userSession;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 创建时间
     */
    private LocalDateTime startTime = LocalDateTime.now();

    /**
     * 用户信息
     */
    private UserDTO user;

    /**
     * 额外数据
     */
    private Map<String, Object> data;

    /**
     * 获取当前的全局参数
     *
     * @param: []
     * @return: com.jason.jlh.common.pojo.IGlobalParam
     * @author:
     * @date: 2020/5/3
     */
    public static IGlobalParam getParam() {
        return LOCAL.get();
    }

    /**
     * 是否管理员
     *
     * @param: []
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public boolean isAdmin() {
        if (user == null) {
            user = loadUserData();
        }
        if (user == null) {
            return false;
        }
        return UsertypeEnum.ADMIN.compare(user.getUserType());
    }

    /**
     * 获取额外数据
     *
     * @param: []
     * @return: java.util.Map
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 加载并缓存用户信息
     *
     * @param: []
     * @return: com.jason.jlh.management.pojo.user.UserDTO
     * @author:
     * @date: 2020/5/3
     */
    protected UserDTO loadUserData() {
        if (user == null) {
            // 获取用户信息
            IUserService userService = SpringContextUtil.getBean(IUserService.class);
            user = userService.selectById(userId);
        }
        return user;
    }
}

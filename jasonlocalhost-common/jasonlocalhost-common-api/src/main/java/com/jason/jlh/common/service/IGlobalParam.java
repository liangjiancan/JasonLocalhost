package com.jason.jlh.common.service;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @title: IGlobalParam
 * @package: com.jason.jlh.common.pojo
 * @description: 全局参数接口
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
public interface IGlobalParam {

    /**
     * 获取用户名
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    String getUserName();

    /**
     * 获取用户ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @NotBlank
    String getUserId();

    /**
     * 获取用户会话标识，即userCode+":"+sessionId
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @NotBlank
    String getUserSession();

    /**
     * 获取用户会话ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @NotBlank
    String getSessionId();

    /**
     * 获取客户端IP
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    String getClientIp();

    /**
     * 是否管理员
     *
     * @param: []
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    boolean isAdmin();

    /**
     * 获取额外数据
     * 
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/3
     */
    Map<String, Object> getData();

}

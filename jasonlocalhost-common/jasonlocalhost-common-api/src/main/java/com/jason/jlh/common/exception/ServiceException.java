package com.jason.jlh.common.exception;

import lombok.Getter;

/**
 * @title: NotImplementException
 * @package: com.jason.jlh.common.exception
 * @description: 服务端业务逻辑类异常
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 3654761380188151037L;

    @Getter
    Object data;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object data) {
        super(message);
        this.data = data;
    }

}

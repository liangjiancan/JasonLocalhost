package com.jason.jlh.common.exception;

import lombok.Getter;

/**
 * @title: WebException
 * @package: com.jason.jlh.common.exception
 * @description: Web层异常
 * @author:
 * @date: 2020/5/14
 * @version: v1.0
 */
public class WebException extends RuntimeException {

    private static final long serialVersionUID = 2603800889295465946L;

    @Getter
    String message;

    @Getter
    Object data;

    public WebException(String message) {
        super(message);
        this.message = message;
    }

    public WebException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }
}

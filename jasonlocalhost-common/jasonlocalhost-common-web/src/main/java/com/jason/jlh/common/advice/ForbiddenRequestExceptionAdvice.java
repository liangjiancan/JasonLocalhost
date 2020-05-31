package com.jason.jlh.common.advice;

import com.jason.jlh.common.pojo.ResponseResult;
import com.jason.jlh.common.support.BaseWebFunction;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * @title: ForbiddenRequestExceptionAdvice
 * @package: com.jason.jlh.common.advice
 * @description: 请求拒绝异常全局处理类
 * @author: huyongjun
 * @date: 2020/5/31
 * @version: v1.0
 */
@Order(2)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@ControllerAdvice
public class ForbiddenRequestExceptionAdvice implements BaseWebFunction {

    /**
     * 全局处理访问禁止异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseResult<Object> exceptionHandler(AccessDeniedException e) {
        return new ResponseResult<>(HttpStatus.FORBIDDEN.toString(), e.getMessage(), getHttpRequestContent());
    }
}

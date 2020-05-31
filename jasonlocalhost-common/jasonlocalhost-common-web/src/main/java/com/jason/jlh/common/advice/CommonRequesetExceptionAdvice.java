package com.jason.jlh.common.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.jason.jlh.common.exception.ServiceException;
import com.jason.jlh.common.exception.WebException;
import com.jason.jlh.common.pojo.ResponseResult;
import com.jason.jlh.common.support.BaseWebFunction;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

/**
 * @title: BadRequesetExceptionAdvice
 * @package: com.jason.jlh.common.advice
 * @description: 一般请求异常全局处理类
 * @author:
 * @date: 2020/5/31
 * @version: v1.0
 */
@Order(1)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@ControllerAdvice
public class CommonRequesetExceptionAdvice implements BaseWebFunction {

    /**
     * 提示消息
     */
    private static final String MSG_DATA_EXIST = "数据已存在";
    private static final String MSG_FOREIGN_KEY_EXCEPTION = "数据一致性错误";
    private static final String MSG_JSON_PARSE_FAIL = "JSON解析异常";

    /**
     * 全局处理主键重复异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseResult<Object> bindExceptionHandler(DuplicateKeyException e) {
        return new ResponseResult<>(HttpStatus.BAD_REQUEST.toString(), MSG_DATA_EXIST, getHttpRequestContent());
    }

    /**
     * 全局处理主键重复异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseResult<Object> bindExceptionHandler(DataIntegrityViolationException e) {
        return new ResponseResult<>(HttpStatus.BAD_REQUEST.toString(), MSG_FOREIGN_KEY_EXCEPTION, getHttpRequestContent());
    }

    /**
     * 全局处理Json解析异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    public ResponseResult<Object> bindExceptionHandler(JsonParseException e) {
        return new ResponseResult<>(false, HttpStatus.BAD_REQUEST.toString(), MSG_JSON_PARSE_FAIL, getHttpRequestContent(), e.getMessage());
    }

    /**
     * 全局处理服务器端返回异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Object> bindExceptionHandler(ServiceException e) {
        return new ResponseResult<>(false, HttpStatus.BAD_REQUEST.toString(), e.getMessage(), getHttpRequestContent(), e.getData());
    }

    /**
     * 全局处理WEB端返回异常
     *
     * @param: [e]
     * @return: com.jason.jlh.common.pojo.ResponseResult<java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @ExceptionHandler(WebException.class)
    @ResponseBody
    public ResponseResult<Object> bindExceptionHandler(WebException e) {
        return new ResponseResult<>(false, HttpStatus.BAD_REQUEST.toString(), e.getMessage(), getHttpRequestContent(), e.getData());
    }
}

package com.jason.jlh.common.service.log;

import com.jason.jlh.common.pojo.log.LogDTO;

/**
 * @title: ILogService
 * @package: com.jason.jlh.common.service
 * @description: 日志服务接口
 * @author: huyongjun
 * @date: 2020/5/24
 * @version: v1.0
 */
public interface ILogService {

    /**
     * 记录日志
     *
     * @param: [logDTO]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/24
     */
    void write(LogDTO logDTO);

}

package com.jason.jlh.common.service.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.jlh.common.pojo.log.LogDTO;
import com.jason.jlh.common.support.BaseServiceFunction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title: BusinessLogService
 * @package: com.jason.jlh.common.service.log
 * @description: 业务日志服务实现
 * @author:
 * @date: 2020/5/24
 * @version: v1.0
 */
@Slf4j
@Component
public class BusinessLogService implements BaseServiceFunction, ILogService {

    private static Logger businessLog = LoggerFactory.getLogger("businessLog");

    /**
     * 记录日志
     *
     * @param logDTO
     * @param: [logInfo]
     * @return: void
     * @author:
     * @date: 2020/5/24
     */
    @Override
    public void write(LogDTO logDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        logDTO.setOperateBy(getUserName());
        logDTO.setOperateById(getUserId());
        logDTO.setOperateTime(new Date());
        try {
            String logInfo = objectMapper.writeValueAsString(logDTO);
            businessLog.info(logInfo);
        } catch (Exception e) {
            log.error("fail to write business log...", e);
        }
    }
}
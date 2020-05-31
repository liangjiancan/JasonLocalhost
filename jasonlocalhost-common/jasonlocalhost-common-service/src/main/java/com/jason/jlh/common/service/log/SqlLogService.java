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
 * @title: ILogService
 * @package: com.jason.jlh.common.service.log
 * @description: SQL日志服务实现
 * @author:
 * @date: 2020/5/24
 * @version: v1.0
 */
@Slf4j
@Component
public class SqlLogService implements BaseServiceFunction, ILogService {

    private static Logger sqlLog = LoggerFactory.getLogger("sqlLog");

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
            sqlLog.info(logInfo);
        } catch (Exception e) {
            log.error("fail to write sql log...", e);
        }
    }
}
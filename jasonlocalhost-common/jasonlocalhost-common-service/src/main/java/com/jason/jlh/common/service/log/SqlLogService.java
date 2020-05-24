package com.jason.jlh.common.service.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.jlh.common.pojo.GlobalParam;
import com.jason.jlh.common.pojo.IGlobalParam;
import com.jason.jlh.common.pojo.log.LogDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @title: ILogService
 * @package: com.jason.jlh.common.service.log
 * @description: SQL日志服务实现
 * @author: huyongjun
 * @date: 2020/5/24
 * @version: v1.0
 */
@Slf4j
@Component
public class SqlLogService implements ILogService {

    private static Logger sqlLog = LoggerFactory.getLogger("sqlLog");

    /**
     * 记录日志
     *
     * @param logDTO
     * @param: [logInfo]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/24
     */
    @Override
    public void write(LogDTO logDTO) {
        IGlobalParam param = GlobalParam.getParam();
        ObjectMapper objectMapper = new ObjectMapper();
        logDTO.setOperateBy(param.getUserName());
        logDTO.setOperateById(param.getUserId());
        logDTO.setOperateTime(LocalDateTime.now());
        try {
            String logInfo = objectMapper.writeValueAsString(logDTO);
            sqlLog.info(logInfo);
        } catch (Exception e) {
            log.error("fail to write sql log...");
        }
    }
}
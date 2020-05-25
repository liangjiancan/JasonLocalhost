package com.jason.jlh.common.service.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.jlh.common.pojo.log.LogDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @title: WebLogService
 * @package: com.jason.jlh.common.service.log
 * @description: 网络日志服务实现
 * @author: huyongjun
 * @date: 2020/5/24
 * @version: v1.0
 */
@Slf4j
@Component
public class WebLogService implements ILogService {

    private static Logger webLog = LoggerFactory.getLogger("webLog");

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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String logInfo = objectMapper.writeValueAsString(logDTO);
            webLog.info(logInfo);
        } catch (Exception e) {
            log.error("fail to write web log...");
        }
    }
}

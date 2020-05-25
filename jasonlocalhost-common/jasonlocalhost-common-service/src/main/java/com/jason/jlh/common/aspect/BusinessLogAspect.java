package com.jason.jlh.common.aspect;

import com.google.common.base.Stopwatch;
import com.jason.jlh.common.pojo.log.BusinessLogDTO;
import com.jason.jlh.common.service.log.ILogService;
import com.jason.jlh.common.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @title: BusinessLogAspect
 * @package: com.jason.jlh.common.aspect
 * @description: 业务日志切面
 * @author: huyongjun
 * @date: 2020/5/24
 * @version: v1.0
 */
@Order(0)
@Slf4j
@Aspect
@Component
public class BusinessLogAspect {

    @Autowired
    @Qualifier("businessLogService")
    private ILogService businessLogService;

    @Value("${jlh.service.log.business.enabled}")
    private boolean businessLogEnabled;

    /**
     * 织入点
     *
     * @param: []
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/25
     */
    @Pointcut("target(com.jason.jlh.common.service.BaseService+)")
    public void logPointCut() {
    }

    /**
     * 环绕通知
     *
     * @param: [joinPoint]
     * @return: java.lang.Object
     * @author: huyongjun
     * @date: 2020/5/25
     */
    @Around(value = "logPointCut()")
    Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取织入点的方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取运行时参数
        Object[] params = joinPoint.getArgs();

        Object result = null;
        String exception = null;
        // 开始计时
        Stopwatch watch = Stopwatch.createStarted();
        try {
            // 执行操作
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            exception = LogUtil.toJsonString(e);
            log.error("服务调用失败:{}", methodName, e);
            throw e;
        } finally {
            // 计算耗时
            try {
                long cost = watch.stop().elapsed(TimeUnit.MILLISECONDS);
                if (businessLogEnabled) {
                    BusinessLogDTO logDTO = new BusinessLogDTO();
                    logDTO.setMethodName(methodName);
                    logDTO.setCostTime(cost);
                    logDTO.setParams(LogUtil.toJsonString(params));
                    logDTO.setResult(LogUtil.toJsonString(result));
                    logDTO.setException(exception);
                    businessLogService.write(logDTO);
                }
            } catch (Exception e) {
                log.error("日志记录失败", e);
            }
        }
    }
}

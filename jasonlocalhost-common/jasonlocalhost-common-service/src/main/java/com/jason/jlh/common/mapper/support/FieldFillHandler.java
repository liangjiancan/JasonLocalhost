package com.jason.jlh.common.mapper.support;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jason.jlh.common.pojo.GlobalParam;
import com.jason.jlh.common.pojo.IGlobalParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @title: FieldFillHandler
 * @package: com.jason.jlh.common.mapper.support
 * @description: 字段填充处理器
 * @author:
 * @date: 2020/5/16
 * @version: v1.0
 */
@Slf4j
public class FieldFillHandler implements MetaObjectHandler {

    /**
     * 新增时自动填充字段
     *
     * @param: [metaObject]
     * @return: void
     * @author:
     * @date: 2020/5/16
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("fill field started...");
        }
        IGlobalParam param = GlobalParam.getParam();
        String userId = param.getUserId();
        String userName = param.getUserName();
        setInsertFieldValByName("modifiedById", userId, metaObject);
        setInsertFieldValByName("modifiedBy", userName, metaObject);
        setInsertFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
        setInsertFieldValByName("createdTime", LocalDateTime.now(), metaObject);
        setInsertFieldValByName("version", 0, metaObject);
        if (log.isDebugEnabled()) {
            log.debug("fill field ended...");
        }
    }

    /**
     * 更新时自动填充字段
     *
     * @param: [metaObject]
     * @return: void
     * @author:
     * @date: 2020/5/16
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("fill field started...");
        }
        IGlobalParam param = GlobalParam.getParam();
        String userId = param.getUserId();
        String userName = param.getUserName();
        setInsertFieldValByName("modifiedById", userId, metaObject);
        setInsertFieldValByName("modifiedBy", userName, metaObject);
        setInsertFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
        if (log.isDebugEnabled()) {
            log.debug("fill field ended...");
        }
    }
}

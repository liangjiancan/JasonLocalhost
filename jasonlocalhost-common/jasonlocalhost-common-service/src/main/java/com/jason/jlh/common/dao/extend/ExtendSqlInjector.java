package com.jason.jlh.common.dao.extend;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;

import java.util.List;

/**
 * @title: ExtendSqlInjector
 * @package: com.jason.jlh.common.dao.injector
 * @description: 增强SQL注入器
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class ExtendSqlInjector extends AbstractSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = getMethodList();
        methodList.add(new UpdateAllColumnById());
        return methodList;
    }
}

package com.jason.jlh.common.mapper.extend;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @title: ExtendSqlInjector
 * @package: com.jason.jlh.common.mapper.extend
 * @description: 增强SQL注入器
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class ExtendSqlInjector extends DefaultSqlInjector {

    /**
     * 重写获取通用Mapper方法列表, 在列表中添加自定义Mapper方法
     *
     * @param: []
     * @return: java.util.List<com.baomidou.mybatisplus.core.injector.AbstractMethod>
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(new UpdateAllColumnById());
        return methodList;
    }
}

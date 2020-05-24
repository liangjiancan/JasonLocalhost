package com.jason.jlh.common.interceptor;

import com.google.common.base.Stopwatch;
import com.jason.jlh.common.pojo.log.SqlLogDTO;
import com.jason.jlh.common.service.log.ILogService;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * @title: SqlLogInterceptor
 * @package: com.jason.jlh.common.interceptor
 * @description: 自定义MyBatis拦截器, 用于记录SQL日志
 * @author: huyongjun
 * @date: 2020/5/23
 * @version: v1.0
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SqlLogInterceptor implements Interceptor {

    @Autowired
    @Qualifier("sqlLogService")
    private ILogService sqlLogService;

    /**
     * 最大字符串长度
     */
    private static final int MAX_STRING_LENGTH = 1500;

    /**
     * 代理对象每次调用的方法，就是要进行拦截的时候要执行的方法
     * 在这个方法里面做我们自定义的逻辑处理
     *
     * @param: [invocation]
     * @return: java.lang.Object
     * @author: huyongjun
     * @date: 2020/5/23
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 开始计时
        Stopwatch watch = Stopwatch.createStarted();
        // 执行拦截对象真正的方法
        Object result = invocation.proceed();
        try {
            // 计算耗时
            long cost = watch.stop().elapsed(TimeUnit.MILLISECONDS);

            // 获取拦截参数
            final Object[] args = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) args[0];
            Object parameters = args[1];
            // 获取执行的Mapper方法的全限名
            String mapperMethodName = mappedStatement.getId();
            // 获取SQL类型
            String commandType = mappedStatement.getSqlCommandType().name();
            // 获取SQL
            BoundSql boundSql = mappedStatement.getBoundSql(parameters);
            String sql = limitStringLength(boundSql.getSql());
            // 获取参数列表
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            ArrayList parameterList = null;
            if (!CollectionUtils.isEmpty(parameterMappings)) {
                parameterList = new ArrayList(parameterMappings.size());
                for (ParameterMapping parameterMapping : parameterMappings) {
                    parameterList.add(parameterMapping.getProperty());
                }
            }

            SqlLogDTO sqlLogDTO = new SqlLogDTO();
            sqlLogDTO.setMethodName(mapperMethodName);
            sqlLogDTO.setCommandType(commandType);
            sqlLogDTO.setSql(sql);
            sqlLogDTO.setParams(String.valueOf(parameterList));
            sqlLogDTO.setCostTime(cost);
            sqlLogDTO.setResult(result instanceof Integer ? (Integer) result : null);
            sqlLogService.write(sqlLogDTO);
        } catch (Throwable e) {
            // 日志记录不抛出异常
        }
        return result;
    }

    /**
     * 包装目标对象 为目标对象创建动态代理
     *
     * @param: [target]
     * @return: java.lang.Object
     * @author: huyongjun
     * @date: 2020/5/23
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 用于在Mybatis配置文件中指定一些属性的，注册当前拦截器的时候可以设置一些属性
     *
     * @param: [properties]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/23
     */
    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 限制字符串长度
     *
     * @param: [str]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/5/24
     */
    private String limitStringLength(String str) {
        if (null == str) {
            return null;
        }
        if (str.length() > MAX_STRING_LENGTH) {
            return str.substring(0, MAX_STRING_LENGTH - 4) + " ...";
        } else {
            return str;
        }
    }

}
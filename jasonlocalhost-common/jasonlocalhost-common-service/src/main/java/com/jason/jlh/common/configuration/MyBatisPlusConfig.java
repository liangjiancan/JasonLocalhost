package com.jason.jlh.common.configuration;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.jason.jlh.common.interceptor.SqlLogInterceptor;
import com.jason.jlh.common.mapper.extend.ExtendSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: MyBatisPlusConfig
 * @package: com.jason.jlh.common.configuration
 * @description: MyBatis-plus配置类
 * @author:
 * @date: 2020/5/16
 * @version: v1.0
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 扩展Mapper方法注入器
     *
     * @param: []
     * @return: com.jason.jlh.common.mapper.extend.ExtendSqlInjector
     * @author:
     * @date: 2020/5/16
     */
    @Bean
    public ExtendSqlInjector mySqlInjector() {
        return new ExtendSqlInjector();
    }

    /**
     * 乐观锁插件, 需在实体类中增加version字段与@Version注解, 并在相应的表中增加version字段
     *
     * @param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
     * @author:
     * @date: 2020/5/16
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页拦截器
     *
     * @param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author:
     * @date: 2020/5/16
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * SQL日志拦截器
     *
     * @param: []
     * @return: com.jason.jlh.common.interceptor.SqlLogInterceptor
     * @author:
     * @date: 2020/5/24
     */
    @Bean
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }
}

package com.jason.jlh.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @title: SpringContextUtil
 * @package: com.jason.jlh.common.utils
 * @description: spring上下文工具类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置Spring上下文对象
     *
     * @param: [applicationContext]
     * @return: void
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
        log.info("ApplicationContext配置成功,applicationContext对象：{}", SpringContextUtil.applicationContext);
    }

    /**
     * 获取spring上下文对象
     *
     * @param: []
     * @return: org.springframework.context.ApplicationContext
     * @author:
     * @date: 2020/5/3
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Bean
     *
     * @param: [name]
     * @return: java.lang.Object
     * @author:
     * @date: 2020/5/3
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取Bean
     *
     * @param: [clazz]
     * @return: T
     * @author:
     * @date: 2020/5/3
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 从Spring容器中获取Bean
     *
     * @param: [name, clazz]
     * @return: T
     * @author:
     * @date: 2020/5/3
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取配置文件中的值
     *
     * @param: [key]
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    public static String getConfigProperty(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    /**
     * 判断Spring容器中是否存在Bean
     *
     * @param: [beanName]
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    /**
     * 判断Bean是否为单例
     *
     * @param: [beanName]
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    public static boolean isSingleton(String beanName) {
        return applicationContext.isSingleton(beanName);
    }

    /**
     * 获取Bean的类型
     *
     * @param: [beanName]
     * @return: java.lang.Class<?>
     * @author:
     * @date: 2020/5/3
     */
    public static Class<?> getType(String beanName) {
        return applicationContext.getType(beanName);
    }

    /**
     * 获取Bean的别名
     *
     * @param: [beanName]
     * @return: java.lang.String[]
     * @author:
     * @date: 2020/5/3
     */
    public static String[] getAliases(String beanName) {
        return applicationContext.getAliases(beanName);
    }

    /**
     * 通过反射调用方法
     *
     * @param: [beanName, methodName, params]
     * @return: java.lang.Object
     * @author:
     * @date: 2020/5/3
     */
    public static Object invokeMethod(String beanName, String methodName, Object[] params) {
        Object bean = getBean(beanName);
        Class<?>[] classes = new Class<?>[params.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = params[i].getClass();
        }
        Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, classes);
        return ReflectionUtils.invokeMethod(method, bean, params);
    }

    /**
     * 获取Bean的类，如果没有返回null
     *
     * @param: [beanDef]
     * @return: java.lang.Class
     * @author:
     * @date: 2020/5/3
     */
    public static Class getBeanClass(BeanDefinition beanDef) {
        // 获取Bean对应的类，由于多处使用AOP，要去掉$$的部分
        if (beanDef.getBeanClassName() == null) {
            return null;
        }
        String targetClass = beanDef.getBeanClassName()
                .split("\\$\\$")[0];

        Class beanClass = null;
        try {
            beanClass = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return beanClass;
    }

    /**
     * 获得特定类型的bean
     *
     * @param: [type]
     * @return: java.util.Map
     * @author:
     * @date: 2020/5/3
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }
}

package com.jason.jlh.common.support.handler;

import com.google.common.base.Splitter;
import com.jason.jlh.common.annotation.BusinessController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * @title: BusinessControllerHandler
 * @package: com.jason.jlh.common.support.handler
 * @description: 注解@BusinessController处理类
 * @author: huyongjun
 * @date: 2020/5/31
 * @version: v1.0
 */
@Slf4j
@Component
public class BusinessControllerHandler implements BeanFactoryPostProcessor {

    private static final String BASE_PACKAGE = "com.jason.jlh.";

    /**
     * 对BeanFactory中的实例进行后置处理
     *
     * @param: [beanFactory]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/31
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNameList = beanFactory.getBeanDefinitionNames();
        for (String name : beanNameList) {
            BeanDefinition definition = beanFactory.getBeanDefinition(name);
            String className = definition.getBeanClassName();
            if (null == className) {
                continue;
            }
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(BusinessController.class)) {
                    BusinessController annotation = clazz.getAnnotation(BusinessController.class);
                    modifyRequestMapping(annotation, clazz);
                }
            } catch (Exception e) {
                log.error("BusinessControllerHandler process fail...", e);
            }
        }
    }

    /**
     * 修改RequestMapping
     *
     * @param: [anno, clazz]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/31
     */
    protected void modifyRequestMapping(BusinessController annotation, Class<?> clazz) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        try {
            Field value = invocationHandler.getClass().getDeclaredField("memberValues");
            value.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
            String[] currentValues = (String[]) memberValues.get("value");
            Assert.notEmpty(currentValues, "@BusinessController should have value...");
            String path = currentValues[0];
            Assert.hasText(path, "@BusinessController's value should not be empty...");

            // 处理模块路径, 包路径和类路径
            String className = clazz.getPackage().getName();
            String modulePath = drawModulePath(className);
            String packagePath = drawPackagePath(className);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }

            if (!path.startsWith(modulePath)) {
                String resultValue = modulePath + packagePath + path;
                memberValues.put("value", new String[]{resultValue});
            }
        } catch (Exception e) {
            log.error("fail to modify RequestMapping...", e);
        }
    }

    /**
     * 提取模块名
     *
     * @param: [fullPackageName]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/6/1
     */
    private String drawModulePath(String fullPackageName) {
        String moduleName = "";
        if (StringUtils.isBlank(fullPackageName)) {
            return moduleName;
        }
        if (fullPackageName.startsWith(BASE_PACKAGE)) {
            moduleName = fullPackageName.substring(BASE_PACKAGE.length(), fullPackageName.indexOf(".", BASE_PACKAGE.length()));
        } else {
            return moduleName;
        }
        if ("common".equals(moduleName)) {
            return "/common";
        }
        return "/" + moduleName;
    }

    /**
     * 提取最后一级包路径
     *
     * @param: [fullPackageName]
     * @return: java.lang.String
     * @author: huyongjun
     * @date: 2020/6/1
     */
    private String drawPackagePath(String fullPackageName) {
        String packageName = "";
        if (StringUtils.isBlank(fullPackageName)) {
            return packageName;
        }
        List<String> packageTokenList = Splitter.on(".").splitToList(fullPackageName);
        String lastPackage = packageTokenList.get(packageTokenList.size() - 1);
        if ("controller".equals(lastPackage)) {
            return packageName;
        }
        packageName = lastPackage;
        return "/" + packageName;
    }
}
package com.jason.jlh.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @title: JlhManagementServiceApplication
 * @package: com.jason.jlh.management.run
 * @description: SpringBoot引导类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@ComponentScan(basePackages = "com.jason")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class JlhManagementWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhManagementWebApplication.class);
    }
}

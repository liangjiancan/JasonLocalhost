package com.jason.jlh.management.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @title: JlhManagementServiceApplication
 * @package: com.jason.jlh.management.run
 * @description: SpringBoot引导类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@MapperScan("com.jason.jlh.**.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class JlhManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhManagementServiceApplication.class);
    }
}

package com.jason.jlh.eureka.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @title: JlhEurekaApplication
 * @package: com.jason.jlh.eureka.run
 * @description: SpringBoot引导类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class JlhEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhEurekaApplication.class);
    }
}

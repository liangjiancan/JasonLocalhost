package com.jason.jlh.picture.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @title: JlhPictureWebApplication
 * @package: com.jason.jlh.picture.run
 * @description: SpringBoot引导类
 * @author:
 * @date: 2020/5/3
 * @version: v1.0
 */
@ComponentScan(basePackages = "com.jason")
@SpringBootApplication
public class JlhPictureWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhPictureWebApplication.class);
    }
}

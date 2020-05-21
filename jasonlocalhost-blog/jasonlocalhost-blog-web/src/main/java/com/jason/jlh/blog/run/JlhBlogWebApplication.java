package com.jason.jlh.blog.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @title: JlhBlogWebApplication
 * @package: com.jason.jlh.blog.run
 * @description: SpringBoot引导类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@ComponentScan(basePackages = "com.jason")
@SpringBootApplication
public class JlhBlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhBlogWebApplication.class);
    }
}

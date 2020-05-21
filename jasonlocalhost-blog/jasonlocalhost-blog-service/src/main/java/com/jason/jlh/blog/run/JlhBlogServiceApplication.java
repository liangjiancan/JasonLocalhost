package com.jason.jlh.blog.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @title: JlhBlogServiceApplication
 * @package: com.jason.jlh.blog.run
 * @description: SpringBoot引导类
 * @author: huyongjun
 * @date: 2020/5/3
 * @version: v1.0
 */
@ComponentScan(basePackages = "com.jason")
@MapperScan("com.jason.jlh.**.dao")
@SpringBootApplication
public class JlhBlogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlhBlogServiceApplication.class);
    }
}

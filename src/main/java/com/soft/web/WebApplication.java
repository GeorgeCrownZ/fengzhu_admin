package com.soft.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan //打成war包需要
//@MapperScan(basePackages ={"com.soft.dao"})//扫描指定包下的所有mapper接口
public class WebApplication extends SpringBootServletInitializer {  //打成war包需要继承SpringBootServletInitializer接口

    @Override  //打成war包需要继承SpringBootServletInitializer接口
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}

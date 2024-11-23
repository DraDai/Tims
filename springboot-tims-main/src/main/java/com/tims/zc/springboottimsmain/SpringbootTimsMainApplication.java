package com.tims.zc.springboottimsmain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tims.zc")
@MapperScan("com.tims.zc.mapper")
public class SpringbootTimsMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTimsMainApplication.class, args);
    }

}

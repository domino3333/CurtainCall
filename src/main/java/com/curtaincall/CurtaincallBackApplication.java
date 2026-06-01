package com.curtaincall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.curtaincall.mapper")
public class CurtaincallBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurtaincallBackApplication.class, args);
    }

}

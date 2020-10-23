package com.example.fangchan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.fangchan.app.mapper")
@EnableTransactionManagement//事务注解
@EnableScheduling//定时任务
@SpringBootApplication
public class FangchanApplication {

    public static void main(String[] args) {
        SpringApplication.run(FangchanApplication.class, args);
    }

}

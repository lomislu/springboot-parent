package com.coeho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务Spring Boot程序启动入口
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-12 17:40
 */
@SpringBootApplication
@EnableAutoConfiguration
public class ScheduledBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledBootApplication.class,args);
    }

}

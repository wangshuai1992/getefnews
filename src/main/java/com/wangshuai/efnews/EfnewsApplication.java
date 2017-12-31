package com.wangshuai.efnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangshuai
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class EfnewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EfnewsApplication.class, args);
    }
}

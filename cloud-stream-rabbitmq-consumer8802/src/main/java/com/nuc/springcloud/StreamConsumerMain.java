package com.nuc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LM
 * @date 2020/6/6 20:16
 * @destription:
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain.class, args);
    }

}

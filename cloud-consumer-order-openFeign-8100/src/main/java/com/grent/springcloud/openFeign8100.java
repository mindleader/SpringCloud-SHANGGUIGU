package com.grent.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class openFeign8100 {
    public static void main(String[] args) {
        SpringApplication.run(openFeign8100.class,args);
    }
}

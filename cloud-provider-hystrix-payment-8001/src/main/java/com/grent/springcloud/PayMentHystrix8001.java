package com.grent.springcloud;

import com.grent.springcloud.entites.Payment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PayMentHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(PayMentHystrix8001.class,args);
    }
}

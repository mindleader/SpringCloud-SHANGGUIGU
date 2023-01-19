package com.grent.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties( defaultFallback = "default_Handler",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
})
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentOK"+id+"成功";
    }

    //@HystrixCommand(fallbackMethod ="paymentInfo_timeoutHandler" ,commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    //})
    @HystrixCommand
    public String paymentInfo_timeout(Integer id){

        Integer time = 2;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {


        }
        //int num=5/0;
        return "线程池"+Thread.currentThread().getName()+"paymentOK"+id+"耗时+"+time+"秒";
    }

    public String paymentInfo_timeoutHandler(Integer id){
       return "线程池"+Thread.currentThread().getName()+"paymentOK"+id+"超时";
    }

    public String default_Handler(){
        return "默认错误或超时返回";
    }

    @HystrixCommand(fallbackMethod = "circuit_Handler",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public  String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw  new RuntimeException("id不能为负数");
        }else{
            String s = IdUtil.simpleUUID();
            return Thread.currentThread().getName()+"调用成功"+s;
        }
    }

    public String circuit_Handler(Integer id){
        return "id"+id+"不能为负数";
    }
}

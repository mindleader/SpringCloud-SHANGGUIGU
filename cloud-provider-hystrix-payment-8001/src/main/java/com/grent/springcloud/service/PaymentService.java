package com.grent.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
}

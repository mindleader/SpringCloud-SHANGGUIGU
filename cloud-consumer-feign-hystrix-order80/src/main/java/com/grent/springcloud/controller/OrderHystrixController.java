package com.grent.springcloud.controller;

import com.grent.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    /**
     * 正常访问
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfo_ok(id);
        return result;
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback",commandProperties =
    //    @HystrixProperty( name = "execution.isolation.thread.timeoutInMilliseconds",value = "3100")
    //)
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        //int num = 10/0;
        String result=paymentHystrixService.paymentInfo_timeout(id);
        return result;

    }
    public String paymentInfo_timeout_fallback(@PathVariable("id") Integer id){
        return "我是80消费者当前方法访问超时或服务异常";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}


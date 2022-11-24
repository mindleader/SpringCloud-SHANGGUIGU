package com.grent.springcloud.service;

import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value="CLOUD-PAYMENT-SERVICE")
public interface PayMentFeignService {

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @PostMapping("/insertPayment")
    public CommonResult<String> insertPayment(@RequestBody Payment payment);


    @PostMapping("/feign/timeout")
    public String feignTimeout();

}

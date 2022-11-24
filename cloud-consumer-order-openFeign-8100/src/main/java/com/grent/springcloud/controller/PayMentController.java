package com.grent.springcloud.controller;

import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;
import com.grent.springcloud.service.PayMentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PayMentController {
    @Autowired
    PayMentFeignService payMentFeignService;

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
      return payMentFeignService.getPayment(id);
    }

    @PostMapping("/insertPayment")
    public CommonResult<String> insertPayment(@RequestBody Payment payment){
        return payMentFeignService.insertPayment(payment);
    }

    @PostMapping("/feign/timeout")
    public String feignTimeout(){
        return payMentFeignService.feignTimeout();
    }
}

package com.grent.springcloud.controller;

import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;
import com.grent.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
//@RequestMapping("/Consumer")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPayment(id);
        return new CommonResult<Payment>(200,"ServerPort:"+serverPort,payment);
    }


    @PostMapping("/insertPayment")
    public CommonResult<String> insertPayment(@RequestBody Payment payment){
        return paymentService.insertPayment(payment);
    }

    @PostMapping("/feign/timeout")
    public String feignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    };

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}

package com.grent.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeignClientService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "Hystrix --错误或超时返回";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "Hystrix --错误或超时返回";
    }
}

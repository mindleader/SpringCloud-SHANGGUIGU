package com.grent.springcloud.service;

import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;

public interface PaymentService {

    public Payment getPayment(Long id);

    public CommonResult<String> insertPayment(Payment payment);
}

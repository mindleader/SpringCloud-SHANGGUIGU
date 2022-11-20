package com.grent.springcloud.service.impl;

import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;
import com.grent.springcloud.mapper.PaymentMapper;
import com.grent.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public Payment getPayment(Long id) {

        Payment payment = paymentMapper.getPayment(id);

        return payment;
    }

    @Override
    public CommonResult<String> insertPayment(Payment payment) {
        Long integer = paymentMapper.insertPayment(payment);
        return new CommonResult<String>(200,"插入成功");
    }
}

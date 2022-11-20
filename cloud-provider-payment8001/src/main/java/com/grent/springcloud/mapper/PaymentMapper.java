package com.grent.springcloud.mapper;

import com.grent.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

   Long insertPayment(Payment payment);

   Payment getPayment(Long id);
}

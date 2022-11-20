package com.grent.springcloud.controller;

import com.grent.springcloud.controller.lb.MyLoadBalance;
import com.grent.springcloud.entites.CommonResult;
import com.grent.springcloud.entites.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    RestTemplate restTemplate;

    @Resource
    MyLoadBalance myLoadBalance;

    private static final String  baseUrl = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(
                baseUrl + "/getPayment/" + id,
                CommonResult.class);

    }

    @GetMapping("/getPaymentByIdWithReponse/{id}")
    public CommonResult<Payment> getPaymentByIdWithReponse(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(
                baseUrl + "/getPayment/" + id,
                CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }
        return new CommonResult<>(400,"错误请求");

    }

    @PostMapping("/insertPayment")
    public CommonResult<String> insertPayment(@RequestBody Payment payment){
        return restTemplate.postForObject(baseUrl+"/insertPayment",payment,CommonResult.class);
    }

    @PostMapping("/getServiceInfo")
    public Object getServiceInfo(){
        List<String> services = discoveryClient.getServices();
        List<String> serviceName = new ArrayList<String>();
        services.forEach(e->{
            serviceName.add(e);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(f -> {
            System.out.println(f.getHost() + " " + f.getUri() + " " + f.getPort());
        });

        return instances;
    }
    @PostMapping("/getServiceInfo/lb")
    public Object getServiceInfoLB(){
        List<String> services = discoveryClient.getServices();
        List<String> serviceName = new ArrayList<String>();
        services.forEach(e->{
            serviceName.add(e);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = myLoadBalance.getSerInstance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(
                uri + "/getPayment/" + 13,
                CommonResult.class);
        //return instances;
    }
}

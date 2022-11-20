package com.grent.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    RestTemplate restTemplate;

    private static final String  baseUrl = "http://cloud-provider-payment8003";

    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/zk")
    public String getPayment(){
        return restTemplate.getForObject(baseUrl+"/payment/zk",String.class);
    }


    @PostMapping("/getServiceInfo")
    public Object getServiceInfo(){
        List<String> services = discoveryClient.getServices();
        List<String> serviceName = new ArrayList<String>();
        services.forEach(e->{
            serviceName.add(e);
        });
        List<ServiceInstance> instances = new ArrayList<>();
        instances.forEach(f -> {
            System.out.println(f.getHost() + " " + f.getUri() + "" + f.getPort());
        });

        return instances;
    }
}

package com.grent.springcloud.controller.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalance {

    ServiceInstance getSerInstance(  List<ServiceInstance> instances);

}

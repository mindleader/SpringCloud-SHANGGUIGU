package com.grent.springcloud.controller.lb;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements MyLoadBalance{
    public static final AtomicInteger automaticInteger = new AtomicInteger(0);
    public final Integer getAndIncrement(){
       Integer current ;
       Integer next = 0;
       do{
           current = automaticInteger.get();
           next = current >Integer.MAX_VALUE?0 :current+1;
           System.out.println("第几次访问"+next);

       }while(!automaticInteger.compareAndSet(current,next));
        return next;
    }
    @Override
    public ServiceInstance getSerInstance(  List<ServiceInstance> instances) {
        Integer index = getAndIncrement() % instances.size();
        return instances.get(index);
    }
}

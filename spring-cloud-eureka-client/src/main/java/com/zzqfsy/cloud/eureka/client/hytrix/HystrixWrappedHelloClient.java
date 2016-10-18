package com.zzqfsy.cloud.eureka.client.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzqfsy.cloud.eureka.client.feign.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("hystrixHelloClient")
public class HystrixWrappedHelloClient implements HelloClient {

    @Autowired
    private HelloClient feignHelloClient;

    @Override
    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall")
    public String hello() {
        return this.feignHelloClient.hello();
    }

    public String fallBackCall() {
        String fallback = ("FAILED SERVICE CALL! - FALLING BACK");
        return fallback;
    }
}

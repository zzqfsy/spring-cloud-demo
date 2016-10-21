package com.zzqfsy.cloud.eureka.client.rpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by john on 16-10-17.
 */

@FeignClient("eurekaservice1")
public interface HelloFeignClient {
    @RequestMapping(value = "/hello", method = GET)
    String hello();
}
package com.zzqfsy.cloud.eureka.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by john on 16-10-17.
 */

@FeignClient("eurekaservice")
public interface HelloClient {
    @RequestMapping(value = "/", method = GET)
    String hello();
}
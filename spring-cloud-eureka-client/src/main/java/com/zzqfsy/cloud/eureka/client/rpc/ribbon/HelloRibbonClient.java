package com.zzqfsy.cloud.eureka.client.rpc.ribbon;

import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by john on 16-10-19.
 */
//@RibbonClient("eurekaservice")
public interface HelloRibbonClient {
    @RequestMapping(value = "/hello", method = GET)
    String hello();
}

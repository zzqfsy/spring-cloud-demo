package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with j360-cloud-all -> me.j360.cloud.eurekaclient.controller.
 * User: min_xu
 * Date: 2015/10/9
 * Time: 10:53
 * 说明：
 */

@RestController
public class HelloController {

    @Autowired
    HelloFeignClient client;

    @Autowired
    @Qualifier("hystrixHelloClient")
    HelloFeignClient hytrixClient;

    /**
     * 直接调用feign，feign会去调用eurekaService
     * */
    @RequestMapping("/")
    public String hello() {
        return client.hello();
    }

    /**
     * 1、调用hytrix
     * 2、hytrix继承并调用feign
     * 3、feign会去调用eurekaService
     * */
    @RequestMapping("/hytrix")
    public String hytrixHello() {
        return hytrixClient.hello();
    }

}

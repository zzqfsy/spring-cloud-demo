package com.zzqfsy.cloud.eureka.service.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 根据eureka实例名${spring.application.name}
     * @return
     */
    @RequestMapping(value = "/serviceUrl/1/{name}", method = RequestMethod.GET)
    public String serviceUrl1(@PathVariable("name") String name) {
        try {
            InstanceInfo instance = eurekaClient.getNextServerFromEureka(name, false);
            return instance.getHomePageUrl();
        }catch (RuntimeException ex){
            return ex.getMessage();
        }
    }

    /**
     * 根据eureka实例名${spring.application.name}
     * @return
     */
    @RequestMapping(value = "/serviceUrl/2/{name}", method = RequestMethod.GET)
    public String serviceUrl2(@PathVariable("name") String name) {
        List<ServiceInstance> list = discoveryClient.getInstances(name);
        if (list != null && list.size() > 0 ) {
            return list.stream()
                       .reduce("", (result, element) -> result +=
                           element.getHost() + ":" + element.getPort() + "," + element.getServiceId() + "<br />",
                               (u, t) -> t);
        }

        return "No matches for the virtual host name :" + name;
    }



    @RequestMapping("/hello")
    public String hello() {
        ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
        return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
    }

}

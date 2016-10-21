package com.zzqfsy.cloud.eureka.client.rpc.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzqfsy.cloud.eureka.client.rpc.feign.CompanyFeignClient;
import com.zzqfsy.cloud.eureka.client.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by john on 16-10-21.
 */
@Service("companyHystrixWrappedClient")
public class CompanyHystrixWrappedClient {
    @Autowired
    private CompanyFeignClient companyFeignClient;

    @HystrixCommand(groupKey = "companyGroup", fallbackMethod = "fallBackCall")
    public Company getCompanys() {
        return companyFeignClient.getCompanys();
    }

    @HystrixCommand(groupKey = "companyGroup2", fallbackMethod = "fallBackCall")
    public Company getCompanyById(Long id) {
        return companyFeignClient.getCompanyById(id);
    }

    public Company fallBackCall() {
        return new Company(1L, "FAILED SERVICE CALL! - FALLING BACK");
    }

    public Company fallBackCall(Long id) {
        return new Company(id, "FAILED SERVICE CALL! - FALLING BACK");
    }
}

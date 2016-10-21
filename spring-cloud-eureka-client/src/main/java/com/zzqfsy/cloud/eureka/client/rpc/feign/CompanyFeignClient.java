package com.zzqfsy.cloud.eureka.client.rpc.feign;

import com.zzqfsy.cloud.eureka.client.model.Company;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by john on 16-10-21.
 */
@FeignClient("eurekaservice1")
public interface CompanyFeignClient {
    @RequestMapping("/api/companys/")
    public Company getCompanys();

    @RequestMapping("/api/companys/{id}")
    public Company getCompanyById(@PathVariable("id") Long id);
}

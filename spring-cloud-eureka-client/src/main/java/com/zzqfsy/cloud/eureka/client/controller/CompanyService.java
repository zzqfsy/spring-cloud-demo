package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.rpc.hytrix.CompanyHystrixWrappedClient;
import com.zzqfsy.cloud.eureka.client.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by john on 16-10-20.
 */
@RestController
public class CompanyService {

    @Autowired
    CompanyHystrixWrappedClient companyHystrixWrappedClient;

    @RequestMapping("/companys")
    public Company getCompanys(){
        return companyHystrixWrappedClient.getCompanys();
    }

    @RequestMapping("/company/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        return companyHystrixWrappedClient.getCompanyById(id);
    }
}

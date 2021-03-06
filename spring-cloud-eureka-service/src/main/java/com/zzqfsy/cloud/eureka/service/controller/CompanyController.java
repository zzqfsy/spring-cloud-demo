package com.zzqfsy.cloud.eureka.service.controller;

import com.zzqfsy.cloud.eureka.service.api.resp.BaseResp;
import com.zzqfsy.cloud.eureka.service.model.Company;
import com.zzqfsy.cloud.eureka.service.model.OneCompany;
import com.zzqfsy.cloud.eureka.service.rpc.hytrix.EmployeeHystrixClient;
import com.zzqfsy.cloud.eureka.service.rpc.hytrix.ProductHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by john on 16-10-20.
 */
@RestController
@RequestMapping(value = "/api/companys")
public class CompanyController {

    @Autowired
    EmployeeHystrixClient employeeHystrixClient;
    @Autowired
    ProductHystrixClient productHystrixClient;

    @RequestMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        //sleep();
        return new Company(id, "Company");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Company getCompanys(){
        sleep();
        return new Company(1L, "Company");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResp createCompanys(@RequestParam("companyId") Long companyId){
        OneCompany oneCompany = new OneCompany();
        oneCompany.setCompany(new Company(companyId, "Company"));
        oneCompany.setEmployees(employeeHystrixClient.getEmployeeByIds(companyId));
        oneCompany.setProducts(productHystrixClient.getProductsByCompanyId(companyId));
        return BaseResp.create(oneCompany);
    }


    //利用时间等待模拟Serivce调用时长
    private void sleep() {
        Random rand = new Random();
        int time = rand.nextInt(2000);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

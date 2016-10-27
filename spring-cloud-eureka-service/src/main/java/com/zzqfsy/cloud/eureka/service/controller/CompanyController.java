package com.zzqfsy.cloud.eureka.service.controller;

import com.zzqfsy.cloud.eureka.service.model.Company;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by john on 16-10-20.
 */
@RestController
@RequestMapping(value = "/api/companys")
public class CompanyController {

    @RequestMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        //sleep();
        return new Company(id, "Company");
    }

    @RequestMapping("/")
    public Company getCompanys(){
        sleep();
        return new Company(1L, "Company");
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

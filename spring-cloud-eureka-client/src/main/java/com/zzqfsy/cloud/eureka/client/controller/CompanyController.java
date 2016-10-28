package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.rpc.failure.ComandCompanyFailure;
import com.zzqfsy.cloud.eureka.client.rpc.failure.ComandCompanyObservableFailure;
import com.zzqfsy.cloud.eureka.client.rpc.hytrix.CompanyHystrixWrappedClient;
import com.zzqfsy.cloud.eureka.client.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by john on 16-10-20.
 */
@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyHystrixWrappedClient companyHystrixWrappedClient;

    @RequestMapping("/companys")
    public Company getCompanys(){
        return companyHystrixWrappedClient.getCompanys();
    }

    @RequestMapping("/companys1")
    public Company getCompanys1(){
        return companyHystrixWrappedClient.getCompanys1();
    }

    @RequestMapping("/companys2")
    public Company getCompanys2(){
        return companyHystrixWrappedClient.getCompanys2();
    }

    @RequestMapping("/companysFutureCollapser")
    public Company getCompanysFutureCollapser(){
        Future<Company> companyFuture = companyHystrixWrappedClient.getCompanyByIdAsync("1");
        try {
            return companyFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/companysFuture")
    public Company getCompanysFuture(){
        Future<Company> companyFuture = companyHystrixWrappedClient.getCompanysFuture();
        try {
            return companyFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/companysObservableCollapser")
    public Company getCompanysObservableCollapser(){
        Observable<Company> companyObservable = companyHystrixWrappedClient.getCompanyByIdReact("1");
        return companyObservable.toBlocking().single();
    }

    @RequestMapping("/companysObservable")
    public Company getCompanysObservable(){
        Observable<Company> companyObservable = companyHystrixWrappedClient.getCompanysObservable();
        return companyObservable.toBlocking().single();
    }

    @RequestMapping("/company/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        return companyHystrixWrappedClient.getCompanyById(id);
    }
}

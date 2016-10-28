package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.rpc.failure.ComandCompanyFailure;
import com.zzqfsy.cloud.eureka.client.rpc.failure.ComandCompanyObservableFailure;
import com.zzqfsy.cloud.eureka.client.rpc.feign.HelloFeignClient;
import com.zzqfsy.cloud.eureka.client.rpc.hytrix.HelloHystrixWrappedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by john on 16-10-28.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    HelloFeignClient client;

    @Autowired
    HelloHystrixWrappedClient hytrixClient;

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


    @RequestMapping("/hytrix2")
    public String hytrixHello2() {
        return new ComandCompanyFailure("ComandCompanyObservableFailure").execute();
    }

    @RequestMapping("/hytrix3")
    public String hytrixHello3() {
        Future<String> fs = new ComandCompanyFailure("ComandCompanyObservableFailure").queue();
        try {
            return fs.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping("/hytrix4")
    public String hytrixHello4() {
        Observable<String> ho = new ComandCompanyObservableFailure("ComandCompanyObservableFailure").observe();
        ho.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Then onCompleted.");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println("Then onError.");
                e.printStackTrace();
            }
            @Override
            public void onNext(String v) {
                System.out.println("Then onNext: " + v);
            }
        });

        ho.subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Then called.");
            }
        });

        return ho.toBlocking().single();
    }
}

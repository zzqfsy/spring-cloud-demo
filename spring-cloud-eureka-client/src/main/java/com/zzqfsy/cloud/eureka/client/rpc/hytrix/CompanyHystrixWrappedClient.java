package com.zzqfsy.cloud.eureka.client.rpc.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.zzqfsy.cloud.eureka.client.rpc.feign.CompanyFeignClient;
import com.zzqfsy.cloud.eureka.client.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * Created by john on 16-10-21.
 */
@Service("companyHystrixWrappedClient")
@DefaultProperties
public class CompanyHystrixWrappedClient {
    @Autowired
    private CompanyFeignClient companyFeignClient;

    @HystrixCommand(fallbackMethod = "fallBackCall")
    public Company getCompanys(Integer i) {
        return companyFeignClient.getCompanys();
    }


    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Company getCompanys1() {
        return companyFeignClient.getCompanys();
    }

    @HystrixProperty(name = "getCompanys2", value = "getCompanys2")
    @HystrixCommand(commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    },  threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "30"),
        @HystrixProperty(name = "maxQueueSize", value = "101"),
        @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
        @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
    }, fallbackMethod = "fallBackCall")
    public Company getCompanys2() {
        return companyFeignClient.getCompanys();
    }

    /** Asynchronous Execution */
    @HystrixCollapser(batchMethod = "getCompanys")
    public Future<Company> getCompanysFutureCollapser(Integer i) {
        return null;
    }
    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Future<Company> getCompanysFuture() {
        return new AsyncResult<Company>() {
            @Override
            public Company invoke() {
                return companyFeignClient.getCompanys();
            }
        };
    }

    /** Reactive Execution */
    @HystrixCollapser(batchMethod = "getCompanys")
    public Observable<Company> getCompanysObservableCollapser(Integer i) {
        return null;
    }
    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Observable<Company> getCompanysObservable() {
        return Observable.create(new Observable.OnSubscribe<Company>() {
            @Override
            public void call(Subscriber<? super Company> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(companyFeignClient.getCompanys());
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    public Company fallBackCall() {
        return new Company(1L, "FAILED SERVICE CALL! - FALLING BACK");
    }


    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Company getCompanyById(Long id) {
        return companyFeignClient.getCompanyById(id);
    }

    public Company fallBackCall(Long id) {
        return new Company(id, "FAILED SERVICE CALL! - FALLING BACK");
    }
}

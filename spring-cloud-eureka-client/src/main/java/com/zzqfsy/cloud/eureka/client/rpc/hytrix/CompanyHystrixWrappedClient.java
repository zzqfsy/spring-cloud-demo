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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by john on 16-10-21.
 */
@Service("companyHystrixWrappedClient")
public class CompanyHystrixWrappedClient {
    @Autowired
    private CompanyFeignClient companyFeignClient;

    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Company getCompanyById(Long id) {
        return companyFeignClient.getCompanyById(id);
    }

    public Company fallBackCall(Long id) {
        return new Company(id, "FAILED SERVICE CALL! - FALLING BACK");
    }

    @HystrixCommand(fallbackMethod = "fallBackCall")
    public Company getCompanys() {
        return companyFeignClient.getCompanys();
    }

    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCall")
    public Company getCompanys1() {
        return companyFeignClient.getCompanys();
    }

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


    @HystrixCommand(groupKey = "eurekaservice1", fallbackMethod = "fallBackCallList")
    public List<Company> getCompanyByIds(String id) {
        return Arrays.asList(companyFeignClient.getCompanys());
    }


    /** Asynchronous Execution */
    @HystrixCollapser(batchMethod = "getCompanyByIds")
    public Future<Company> getCompanyByIdAsync(String id) {
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
    @HystrixCollapser(batchMethod = "getCompanyByIds")
    public Observable<Company> getCompanyByIdReact(String id) {
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

    public List<Company> fallBackCallList() {
        return Arrays.asList(new Company(1L, "FAILED SERVICE CALL! - FALLING BACK"));
    }
}

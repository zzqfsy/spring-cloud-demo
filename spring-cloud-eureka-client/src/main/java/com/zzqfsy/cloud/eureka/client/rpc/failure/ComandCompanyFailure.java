package com.zzqfsy.cloud.eureka.client.rpc.failure;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by john on 16-10-27.
 */
public class ComandCompanyFailure extends HystrixCommand<String> {

    private final String name;

    public ComandCompanyFailure(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Company";
    }

    @Override
    protected String getFallback() {
        return "Company " + name + "!";
    }
}
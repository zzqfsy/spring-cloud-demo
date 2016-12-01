package com.zzqfsy.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by john on 16-10-17.
 */
@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class ZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
}

package com.zzqfsy.cloud.eureka.service.rpc.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzqfsy.cloud.eureka.service.rpc.feign.ProductFeignClient;
import com.zzqfsy.cloud.eureka.service.rpc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by john on 16-10-21.
 */
@Service("productHystrixHelloClient")
public class ProductHystrixClient {
    @Autowired
    private ProductFeignClient productFeignClient;

    @HystrixCommand(groupKey = "productGroup", fallbackMethod = "fallBackCall")
    public Product getProductById(Long id) {
        return productFeignClient.getProductById(id);
    }

    @HystrixCommand(groupKey = "productGroup2", fallbackMethod = "fallBackCallList")
    public List<Product> getProductsByCompanyId(Long companyId) {
        return productFeignClient.getProductsByCompanyId(companyId);
    }

    public Product fallBackCall(Long id) {
        return new Product(id, id, "FAILED SERVICE CALL! - FALLING BACK");
    }

    public List<Product> fallBackCallList(Long id) {
        return Arrays.asList(new Product(id, id, "FAILED SERVICE CALL! - FALLING BACK"));
    }
}

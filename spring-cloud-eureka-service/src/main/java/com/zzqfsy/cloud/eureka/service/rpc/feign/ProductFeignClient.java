package com.zzqfsy.cloud.eureka.service.rpc.feign;

import com.zzqfsy.cloud.eureka.service.rpc.model.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by john on 16-10-21.
 */
@FeignClient("eureka-server")
public interface ProductFeignClient {
    @RequestMapping(value = "/api/products/{id}", method = RequestMethod.GET)
    Product getProductById(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/products/", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> getProductsByCompanyId(@RequestParam("companyId") Long companyId);
}

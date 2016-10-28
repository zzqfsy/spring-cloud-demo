package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.rpc.hytrix.ProductHystrixWrappedClient;
import com.zzqfsy.cloud.eureka.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by john on 16-10-21.
 */
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductHystrixWrappedClient productHystrixWrappedClient;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product getProductById(@PathVariable("id") Long id){
        return productHystrixWrappedClient.getProductById(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    List<Product> getProductsByCompanyId(){
        return productHystrixWrappedClient.getProductsByCompanyId(20L);
    }
}

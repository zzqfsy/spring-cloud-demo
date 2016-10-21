package com.zzqfsy.cloud.eureka.server.controller;

import com.zzqfsy.cloud.eureka.server.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by john on 16-10-20.
 */
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Long id) {
        //sleep();
        return new Product(id, 1L, "T001");
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getProductsByCompanyId(@RequestParam("companyId") Long companyId) {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, companyId, "T001"));
        products.add(new Product(2L, companyId, "T002"));
        products.add(new Product(3L, companyId, "T003"));
        return products;
    }


    private void sleep() {
        Random rand = new Random();
        int time = rand.nextInt(3000);

        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

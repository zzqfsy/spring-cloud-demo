package com.zzqfsy.cloud.eureka.service.rpc.model;

/**
 * Created by john on 16-10-20.
 */
public class Product {

    private Long id;
    private Long companyId;
    private String sku;

    // Constructor、Getter、Setter

    public Product() {
    }

    public Product(Long id, Long companyId, String sku) {
        this.id = id;
        this.companyId = companyId;
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}

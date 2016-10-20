package com.zzqfsy.cloud.eureka.dashboard.model;

/**
 * Created by john on 16-10-20.
 */
public class Employee {

    private Long id;
    private Long companyId;
    private String name;

    // Constructor、Getter、Setter

    public Employee(Long id, Long companyId, String name) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
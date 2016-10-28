package com.zzqfsy.cloud.eureka.service.model;

import com.zzqfsy.cloud.eureka.service.rpc.model.Employee;
import com.zzqfsy.cloud.eureka.service.rpc.model.Product;

import java.util.List;

/**
 * Created by john on 16-10-28.
 */
public class OneCompany {
    Company company;
    List<Employee> employees;
    List<Product> products;

    public OneCompany() {
    }

    public OneCompany(
        Company company,
        List<Employee> employees,
        List<Product> products
    ) {
        this.company = company;
        this.employees = employees;
        this.products = products;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

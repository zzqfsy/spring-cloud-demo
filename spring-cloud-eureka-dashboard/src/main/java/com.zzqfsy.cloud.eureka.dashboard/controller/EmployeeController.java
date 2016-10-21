package com.zzqfsy.cloud.eureka.dashboard.controller;

import com.zzqfsy.cloud.eureka.dashboard.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by john on 16-10-20.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @RequestMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {

        sleep();
        return new Employee(id,1L,"张三");
    }

    @RequestMapping("")
    public List<Employee> getEmployeesByCompanyId(@RequestParam("companyId") Long companyId){
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1L, companyId, "张三"));
        employees.add(new Employee(2L, companyId, "李四"));
        employees.add(new Employee(3L, companyId, "王五"));

        sleep();
        return employees;
    }

    private void sleep() {
        Random rand = new Random();
        int time = rand.nextInt(2000);

        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

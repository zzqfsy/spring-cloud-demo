package com.zzqfsy.cloud.eureka.client.controller;

import com.zzqfsy.cloud.eureka.client.model.Employee;
import com.zzqfsy.cloud.eureka.client.rpc.hytrix.EmployeeHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by john on 16-10-28.
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeHystrixClient employeeHystrixClient;

    @RequestMapping("/employees/1")
    public Employee getEmployeeById(){
        return employeeHystrixClient.getEmployeeById(1L);
    }

    @RequestMapping("/employees")
    public List<Employee> getEmployeeByIds(){
        return employeeHystrixClient.getEmployeeByIds(1L);
    }

}

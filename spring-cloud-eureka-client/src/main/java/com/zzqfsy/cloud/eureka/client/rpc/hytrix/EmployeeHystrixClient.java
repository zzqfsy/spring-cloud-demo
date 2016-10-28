package com.zzqfsy.cloud.eureka.client.rpc.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzqfsy.cloud.eureka.client.model.Employee;
import com.zzqfsy.cloud.eureka.client.rpc.feign.EmployeeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by john on 16-10-28.
 */
@Service("employeeHystrixClient")
public class EmployeeHystrixClient {
    @Autowired
    EmployeeFeignClient employeeFeignClient;

    @HystrixCommand(groupKey = "eurekadashboard", fallbackMethod = "fallBackCall")
    public Employee getEmployeeById(Long companyId) {
        return employeeFeignClient.getEmployeeById(companyId);
    }

    @HystrixCommand(groupKey = "eurekadashboard", fallbackMethod = "fallBackCallList")
    public List<Employee> getEmployeeByIds(Long companyId) {
        return employeeFeignClient.getEmployeesByCompanyId(companyId);
    }


    Employee fallBackCall(Long companyId){
        return new Employee(0L, 0L, "error");
    }

    List<Employee> fallBackCallList(Long companyId){
        return Arrays.asList(new Employee(0L, 0L, "error"));
    }
}

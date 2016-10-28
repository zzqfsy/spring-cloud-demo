package com.zzqfsy.cloud.eureka.client.rpc.feign;

import com.zzqfsy.cloud.eureka.client.model.Employee;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by john on 16-10-28.
 */
@FeignClient("eurekadashboard")
public interface EmployeeFeignClient {
    @RequestMapping("/api/employees/{id}")
    Employee getEmployeeById(@PathVariable("id") Long id);

    @RequestMapping("/api/employees/")
    List<Employee> getEmployeesByCompanyId(@RequestParam("companyId") Long companyId);
}

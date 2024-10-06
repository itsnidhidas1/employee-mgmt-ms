package com.myassignment.employeemgmtms.controller;

import com.myassignment.employeemgmtms.models.EmployeeDto;
import com.myassignment.employeemgmtms.service.IEmployeeService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
//@RequestMapping("employee-mgmt")
public class EmployeeMgmtController {

    @Autowired
    private final IEmployeeService employeeService;

    /*@GetMapping("/employees")
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }*/

    @PostMapping("/employee")
    public Long createEmployees(@RequestBody EmployeeDto employeeDto) {

        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployeesWithAgeAndTitleFilter(@RequestParam (value = "age", required = false) Integer age, @RequestParam(value = "title", required = false) String title) {
        if(age !=null && age > 0 && StringUtils.isNotEmpty(title)) {
            return employeeService.getEmployeesWithFilteredAgeAndTitle(age, title);
        }else {
            return employeeService.getEmployees();
        }
    }
}

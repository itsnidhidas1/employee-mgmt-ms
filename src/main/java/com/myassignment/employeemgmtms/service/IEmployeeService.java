package com.myassignment.employeemgmtms.service;

import com.myassignment.employeemgmtms.models.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    public List<EmployeeDto> getEmployees();
    public Long createEmployee(EmployeeDto employeeDto);

    public List<EmployeeDto> getEmployeesWithFilteredAgeAndTitle(int age, String title);
}

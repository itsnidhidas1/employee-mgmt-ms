package com.myassignment.employeemgmtms.repository;

import com.myassignment.employeemgmtms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Period;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByTitle(String title);
}

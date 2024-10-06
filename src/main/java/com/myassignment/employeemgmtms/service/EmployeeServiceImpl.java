package com.myassignment.employeemgmtms.service;

import com.myassignment.employeemgmtms.entity.Employee;
import com.myassignment.employeemgmtms.models.EmployeeDto;
import com.myassignment.employeemgmtms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return mapEmployeeListToEmployeeDtoList(employeeList);
    }

    private List<EmployeeDto> mapEmployeeListToEmployeeDtoList(List<Employee> employeeList) {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeList.forEach(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setAddress(employee.getAddress());
            employeeDto.setName(employee.getName());
            employeeDto.setSin(employee.getSin());
            employeeDto.setId(employee.getId());
            employeeDto.setPhoneNumber(employee.getPhoneNumber());
            employeeDto.setTitle(employee.getTitle());
            employeeDto.setDateOfBirth(employee.getDateOfBirth().toString());
            employeeDtos.add(employeeDto);
        });
        return employeeDtos;
    }

    @Override
    public Long createEmployee(EmployeeDto employeeDto) {
        //map employeeDto to Entity Employee
        employeeDto.setSin(employeeDto.getSin().substring(employeeDto.getSin().length() - 4));
        Employee employeeEntity = mapEmployeeDtoToEmployeeEntity(employeeDto);
        Employee employee = employeeRepository.save(employeeEntity);
        return employee.getId();
    }

    @Override
    public List<EmployeeDto> getEmployeesWithFilteredAgeAndTitle(int age, String title) {
        List<Employee> empListByTitle = employeeRepository.findByTitle(title);
        List<Employee> filteredList = empListByTitle.stream().filter(emp -> ageCalculator(emp.getDateOfBirth()) > age).collect(Collectors.toList());
        return mapEmployeeListToEmployeeDtoList(filteredList);
    }

    private Employee mapEmployeeDtoToEmployeeEntity(EmployeeDto employeeDto) {
        Employee employeeEntity = new Employee();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setAddress(employeeDto.getAddress());
        employeeEntity.setSin(employeeDto.getSin());
        employeeEntity.setDateOfBirth(LocalDate.parse(employeeDto.getDateOfBirth()));
        employeeEntity.setTitle(employeeDto.getTitle());
        employeeEntity.setPhoneNumber(employeeDto.getPhoneNumber());
        return employeeEntity;
    }

    public int ageCalculator(LocalDate dobFromDB) {
        LocalDate currentDate = LocalDate.now();
        if (dobFromDB != null) {
            return Period.between(dobFromDB, currentDate).getYears();
        } else {
            return 0; //error message;
        }
    }
}

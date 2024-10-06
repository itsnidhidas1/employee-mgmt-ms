package com.myassignment.employeemgmtms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeDto {
    private long id;
    private String name;
    private String address;
    private String dateOfBirth;
    private String phoneNumber;
    private String title;
    private String sin;

}

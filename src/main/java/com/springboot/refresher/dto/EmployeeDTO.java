package com.springboot.refresher.dto;

import com.springboot.refresher.entity.Department;
import com.springboot.refresher.entity.Gender;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long empNo;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Department department;
    private String birthDate;
    private String hireDate;
}

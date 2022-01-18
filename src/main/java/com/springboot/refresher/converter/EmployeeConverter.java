package com.springboot.refresher.converter;

import com.springboot.refresher.dto.EmployeeDTO;
import com.springboot.refresher.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    private final Employee employee;
    private final EmployeeDTO employeeDTO;

    public EmployeeConverter(@Autowired Employee employee, @Autowired EmployeeDTO employeeDTO) {
        this.employee = employee;
        this.employeeDTO = employeeDTO;
    }

    public Employee dtoToEntity(EmployeeDTO employeeDTO) {
        employee.setEmpNo(employeeDTO.getEmpNo());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setHireDate(employeeDTO.getHireDate());
        return employee;
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTOList) {
        return employeeDTOList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public EmployeeDTO entityToDTO(Employee employee) {
        employeeDTO.setEmpNo(employee.getEmpNo());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setHireDate(employee.getHireDate());
        return employeeDTO;
    }

    public Page<EmployeeDTO> entityToDTO(Page<Employee> employeeList) {
        return new PageImpl<>(employeeList.stream().map(this::entityToDTO).collect(Collectors.toList()));
    }
}

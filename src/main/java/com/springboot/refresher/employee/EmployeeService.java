package com.springboot.refresher.employee;

import com.springboot.refresher.employee.Employee;
import com.springboot.refresher.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> e = employeeRepository.findById(id);
        return e.get();
    }

    public HttpStatus createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return HttpStatus.CREATED;
    }
}

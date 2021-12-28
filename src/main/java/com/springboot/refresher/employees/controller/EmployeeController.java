package com.springboot.refresher.employees.controller;

import com.springboot.refresher.employees.repository.EmployeeRepository;
import com.springboot.refresher.employees.service.EmployeeService;
import com.springboot.refresher.employees.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> eList = employeeRepository.findAll();
        return ResponseEntity.ok().body(eList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity
            <Optional<Employee>> getEmployee(@PathVariable int id) {
        Optional<Employee> e = employeeRepository.findById(id);
        return ResponseEntity.ok().body(e);
    }

    @PostMapping("/")
    public HttpStatus addEmployee(@RequestBody Employee employee) {
        if (employeeRepository.findById(employee.getId()).isEmpty()) {
            employeeRepository.save(employee);
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.CONFLICT;
        }
    }
}

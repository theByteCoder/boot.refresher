package com.springboot.refresher.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok().body(employeeService.getEmployees());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity
            <Employee> getEmployee(@PathVariable long id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @PostMapping("/")
    public HttpStatus addEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }
}

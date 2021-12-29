package com.springboot.refresher.employee;

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

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> eList = employeeService.getEmployees();
        return ResponseEntity.ok().body(eList);
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

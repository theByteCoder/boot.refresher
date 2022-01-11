package com.springboot.refresher.employee;

import com.sun.istack.NotNull;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(
            @RequestParam @NotNull int page,
            @RequestParam @NotNull int size) {
        return ResponseEntity.ok().body(employeeService.getEmployees(page, size));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity
            <Employee> getEmployee(@PathVariable long id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@RequestBody @NonNull Employee employee) {
        return employeeService.createEmployee(employee);
    }
}

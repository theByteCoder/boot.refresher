package com.springboot.refresher.controller;

import com.springboot.refresher.entity.Employee;
import com.springboot.refresher.service.EmployeeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final CacheControl cacheControl = CacheControl
            .maxAge(60, TimeUnit.MINUTES)
            .noTransform()
            .mustRevalidate();

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam @NonNull int page,
                                                       @RequestParam @NonNull int size) {
        return ResponseEntity.ok().cacheControl(cacheControl).body(employeeService.getEmployees(page, size));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity
            <Employee> getEmployee(@PathVariable long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent())
            return ResponseEntity.ok().cacheControl(cacheControl).body(employee.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@RequestBody @NonNull Employee employee) {
        return employeeService.createEmployee(employee);
    }
}

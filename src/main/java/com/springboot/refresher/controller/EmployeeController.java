package com.springboot.refresher.controller;

import com.springboot.refresher.converter.EmployeeConverter;
import com.springboot.refresher.dto.EmployeeDTO;
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

    private final EmployeeService employeeService;
    private final EmployeeConverter employeeConverter;

    public EmployeeController(@Autowired EmployeeService employeeService, @Autowired EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeConverter = employeeConverter;
    }

    private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.MINUTES).noTransform().mustRevalidate();

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getEmployees(@RequestParam @NonNull int page, @RequestParam @NonNull int size) {
        Page<EmployeeDTO> employeeDTOPage = employeeConverter.entityToDTO(employeeService.getEmployees(page, size));
        return ResponseEntity.ok().cacheControl(cacheControl).body(employeeDTOPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            EmployeeDTO employeeDTO = employeeConverter.entityToDTO(employee.get());
            return ResponseEntity.ok().cacheControl(cacheControl).body(employeeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> addEmployee(@RequestBody @NonNull EmployeeDTO employeeDTO) {
        Employee employee = employeeConverter.dtoToEntity(employeeDTO);
        return employeeService.createEmployee(employee);
    }
}

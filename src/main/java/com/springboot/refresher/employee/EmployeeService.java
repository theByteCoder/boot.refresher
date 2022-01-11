package com.springboot.refresher.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "emps")
    public Page<Employee> getEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return employeeRepository.findAll(pageable);
    }

    @Cacheable(value = "emp")
    public Employee getEmployeeById(Long id) {
        Optional<Employee> e = employeeRepository.findById(id);
        return e.get();
    }

    public ResponseEntity<Employee> createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }
}

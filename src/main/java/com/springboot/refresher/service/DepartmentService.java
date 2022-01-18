package com.springboot.refresher.service;

import com.springboot.refresher.entity.Department;
import com.springboot.refresher.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> d = departmentRepository.findById(id);
        return d.get();
    }

    public HttpStatus createDepartment(Department department) {
        departmentRepository.save(department);
        return HttpStatus.CREATED;
    }
}

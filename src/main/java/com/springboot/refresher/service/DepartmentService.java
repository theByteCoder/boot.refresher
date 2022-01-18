package com.springboot.refresher.service;

import com.springboot.refresher.entity.Department;
import com.springboot.refresher.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> getDepartments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return departmentRepository.findAll(pageable);
    }

    public Optional<Department> getDepartmentByDeptNo(Long id) {
        return departmentRepository.findByDeptNo(id);
    }

    public HttpStatus createDepartment(Department department) {
        Optional<Department> departmentOptional = departmentRepository.findByDeptNo(department.getDeptNo());
        if (departmentOptional.isPresent()) return HttpStatus.CONFLICT;
        departmentRepository.save(department);
        return HttpStatus.CREATED;
    }
}

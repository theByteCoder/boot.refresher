package com.springboot.refresher.controller;

import com.springboot.refresher.entity.Department;
import com.springboot.refresher.service.DepartmentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final CacheControl cacheControl = CacheControl
            .maxAge(60, TimeUnit.MINUTES)
            .noTransform()
            .mustRevalidate();

    @Autowired
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Page<Department>> getDepartments(
            @RequestParam @NonNull int page,
            @RequestParam @NonNull int size
    ) {
        return ResponseEntity.ok().body(departmentService.getDepartments(page, size));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent())
            return ResponseEntity.ok().cacheControl(cacheControl).body(department.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    public HttpStatus createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }
}

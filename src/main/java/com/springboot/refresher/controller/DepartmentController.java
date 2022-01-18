package com.springboot.refresher.controller;

import com.springboot.refresher.converter.DepartmentConverter;
import com.springboot.refresher.dto.DepartmentDTO;
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

    private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.MINUTES).noTransform().mustRevalidate();

    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    public DepartmentController(@Autowired DepartmentService departmentService, @Autowired DepartmentConverter departmentConverter) {
        this.departmentService = departmentService;
        this.departmentConverter = departmentConverter;
    }

    @GetMapping
    public ResponseEntity<Page<Department>> getDepartments(@RequestParam @NonNull int page, @RequestParam @NonNull int size) {
        return ResponseEntity.ok().body(departmentService.getDepartments(page, size));
    }

    @GetMapping(value = "/{deptNo}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long deptNo) {
        Optional<Department> department = departmentService.getDepartmentByDeptNo(deptNo);
        if (department.isPresent()) {
            DepartmentDTO departmentDTO = departmentConverter.entityToDTO(department.get());
            return ResponseEntity.ok().cacheControl(cacheControl).body(departmentDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    public HttpStatus createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentConverter.dtoToEntity(departmentDTO);
        return departmentService.createDepartment(department);
    }
}

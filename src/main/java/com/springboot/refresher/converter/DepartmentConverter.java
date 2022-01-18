package com.springboot.refresher.converter;

import com.springboot.refresher.dto.DepartmentDTO;
import com.springboot.refresher.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DepartmentConverter {

    public Department dtoToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDeptNo(departmentDTO.getDeptNo());
        department.setDeptName(departmentDTO.getDeptName());
        return department;
    }

    public DepartmentDTO entityToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptNo(department.getDeptNo());
        departmentDTO.setDeptName(department.getDeptName());
        return departmentDTO;
    }

    public Page<Department> dtoToEntity(Page<DepartmentDTO> departmentDTOPage) {
        return new PageImpl<>(departmentDTOPage.stream().map(this::dtoToEntity).collect(Collectors.toList()));
    }

    public Page<DepartmentDTO> entityToDTO(Page<Department> departmentPage) {
        return new PageImpl<>(departmentPage.stream().map(this::entityToDTO).collect(Collectors.toList()));
    }
}

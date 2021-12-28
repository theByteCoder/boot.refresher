package com.springboot.refresher.employees.repository;

import com.springboot.refresher.employees.entity.Gender;
import com.springboot.refresher.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByGender(Gender gender);

}

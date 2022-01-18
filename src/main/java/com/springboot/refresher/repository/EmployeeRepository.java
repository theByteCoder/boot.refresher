package com.springboot.refresher.repository;

import com.springboot.refresher.entity.Employee;
import com.springboot.refresher.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByGender(Gender gender);

}

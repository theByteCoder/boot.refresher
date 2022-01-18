package com.springboot.refresher.repository;

import com.springboot.refresher.entity.Employee;
import com.springboot.refresher.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByGender(Gender gender);

    Optional<Employee> findByEmpNo(Long aLong);
}

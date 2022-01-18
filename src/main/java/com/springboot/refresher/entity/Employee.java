package com.springboot.refresher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_no")
    private Long empNo;

    @Column(name = "first_name", length = 225)
    private String firstName;

    @Column(name = "last_name", length = 225)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "hire_date", nullable = false)
    private String hireDate;
}

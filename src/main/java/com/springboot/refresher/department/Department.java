package com.springboot.refresher.department;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no")
    private Long deptNo;

    @Column(name = "dept_name", nullable = false)
    private String deptName;
}

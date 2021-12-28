package com.springboot.refresher.employees.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private int emp_no;

    @Column(name = "first_name", length = 225)
    private String firstName;

    @Column(name = "last_name", length = 225)
    private String lastName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Gender gender, Date birthDate, Date hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
    }

    public int getId() {
        return emp_no;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                '}';
    }
}

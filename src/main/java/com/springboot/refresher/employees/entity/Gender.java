package com.springboot.refresher.employees.entity;

public enum Gender {
    MALE(0), FEMALE(1), OTHER(2);

    private final int genderCode;

    private Gender(int genderCode) {
        this.genderCode = genderCode;
    }

    public int getGenderCode() {
        return this.genderCode;
    }
}

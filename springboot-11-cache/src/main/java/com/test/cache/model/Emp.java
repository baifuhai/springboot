package com.test.cache.model;

import java.io.Serializable;

public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String lastName;

    private String email;

    private String gender;

    private Integer deptId;

    private Dept dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", deptId=" + deptId + ", dept=" + dept + "]";
    }

}

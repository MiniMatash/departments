package com.minimatash.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Column(name = "departmentName")
    private String departmentName;

    @Id
    @Column(name = "departmentId")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer departmentId;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String depName) {
        this.departmentName = depName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer depId) {
        this.departmentId = depId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }

    public Department(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public Department()
    {
        departmentName =null;
        departmentId =null;
    }
}

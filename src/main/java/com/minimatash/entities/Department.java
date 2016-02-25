package com.minimatash.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Column(name = "depName")
    private String depName;

    @Id
    @Column(name = "depId")
    @GeneratedValue
    private Integer depId;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depName='" + depName + '\'' +
                ", depId='" + depId + '\'' +
                '}';
    }

    public Department(String depName)
    {
        this.depName =depName;
    }

    public Department()
    {
        depName =null;
        depId =null;
    }
}

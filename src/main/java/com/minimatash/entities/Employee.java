package com.minimatash.entities;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Id
    @Column(name = "empId")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer employeeId;

    @Column(name = "depId")
    private Integer departmentId;

    @Column(name = "dateOfBirth")
    @Temporal(value=TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "validityOfContract")
    private Integer validityOfContract;


    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer id) { this.employeeId = id;  }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getValidityOfContract() {
        return validityOfContract;
    }

    public void setValidityOfContract(Integer validityOfContract) {
        this.validityOfContract = validityOfContract;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeId=" + employeeId +
                ", departmentId=" + departmentId +
                ", dateOfBirth=" + dateOfBirth +
                ", validityOfContract=" + validityOfContract +
                '}';
    }

    public Employee(String aFname, String aLname, int aDep_id, Date aDateOfBirth, int aValidityOfContract)
    {
        firstName = aFname;
        lastName = aLname;
        departmentId =aDep_id;
        dateOfBirth=aDateOfBirth;
        validityOfContract=aValidityOfContract;
    }

    public Employee()
    {
        firstName = null;
        lastName = null;
        departmentId =null;
        dateOfBirth=null;
        validityOfContract=null;
    }


}

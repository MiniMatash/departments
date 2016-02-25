package com.minimatash.persistence;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.PersistenceException;

import java.util.List;

public interface EmployeePersistence {
    public List<Employee> findAll() throws PersistenceException;
    public Employee findOne(Integer search_Id) throws PersistenceException;
    public void create(Employee employee) throws PersistenceException;
    public void update(Integer empid,Employee employee);
    public void delete(Integer id);
}

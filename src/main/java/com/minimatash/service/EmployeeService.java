package com.minimatash.service;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll() throws ServiceException;

    public Employee findOne(Integer search_Id) throws ServiceException;

    public void create(Employee employee) throws ServiceException;

    public void update(Integer empid, Employee employee);

    public void delete(Integer id);
    }


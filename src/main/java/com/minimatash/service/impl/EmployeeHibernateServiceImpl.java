package com.minimatash.service.impl;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.persistence.EmployeePersistence;
import com.minimatash.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class EmployeeHibernateServiceImpl implements EmployeeService {


    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    @Qualifier("employeeHibernatePersistenceImpl")
    private EmployeePersistence employeePersistence ;

    @Override
    public List<Employee> findAll() throws ServiceException {
        try {
            return employeePersistence.findAll();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Employee findOne(Integer search_Id) throws ServiceException {
        try {
            return employeePersistence.findOne(search_Id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void create(Employee employee) throws ServiceException {
        try {
            employeePersistence.create(employee);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Employee employee) {
        employeePersistence.update(employee);
    }

    @Override
    public void delete(Integer id) {
        employeePersistence.delete(id);
    }
}

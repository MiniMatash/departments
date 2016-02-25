package com.minimatash.service.impl;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.persistence.DepartmentPersistence;
import com.minimatash.persistence.impl.DepartmentPersistenceImpl;
import com.minimatash.service.DepartmentService;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static DepartmentPersistence departmentPersistence = new DepartmentPersistenceImpl();

    @Override
    public List<Department> findAll() throws ServiceException {
        try {
            return departmentPersistence.findAll();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Department findOne(Integer searchId) throws ServiceException {
        try {
            return departmentPersistence.findOne(searchId);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void create(Department department) throws ServiceException {
        try {
            departmentPersistence.create(department);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Integer depId, Department department) {
        departmentPersistence.update(depId, department);
    }

    @Override
    public void delete(Integer id) {
        departmentPersistence.delete(id);
    }
}

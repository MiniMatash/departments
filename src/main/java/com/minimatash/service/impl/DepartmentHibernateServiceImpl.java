package com.minimatash.service.impl;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.persistence.DepartmentPersistence;
import com.minimatash.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DepartmentHibernateServiceImpl implements DepartmentService {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Autowired
    @Qualifier("departmentHibernatePersistenceImpl")
    private DepartmentPersistence departmentPersistence ;

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
    public void create(Department dept) throws ServiceException {
        try {
            departmentPersistence.create(dept);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Department dep) {
        departmentPersistence.update(dep);
    }

    @Override
    public void delete(Integer deptId) {
        departmentPersistence.delete(deptId);
    }
}

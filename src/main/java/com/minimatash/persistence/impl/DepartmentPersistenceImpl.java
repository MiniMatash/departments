package com.minimatash.persistence.impl;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.others.DepartmentRowMapper;
import com.minimatash.others.DepartmentSearcher;
import com.minimatash.persistence.DepartmentPersistence;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepartmentPersistenceImpl implements DepartmentPersistence{

    static ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
    static JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");

    @Override
    public List<Department> findAll()  throws PersistenceException
    {
        String selectTableSQL = "SELECT * from DEPARTMENT";

        List<Department> departments = new ArrayList<>();
        List<Map<String,Object>> rows = jdbcTemplate1.queryForList(selectTableSQL);
        DepartmentSearcher departmentSearcher = new DepartmentSearcher();
        departmentSearcher.search(rows,departments);
        return departments;
    }

    @Override
    public Department findOne(Integer searchId) throws PersistenceException {
        String selectTableSQL = "SELECT * from Department WHERE departmentId=?";
        Department department = (Department)jdbcTemplate1.queryForObject(
                selectTableSQL, new Object[] { searchId }, new DepartmentRowMapper());
        return department;
    }

    @Override
    public void create(Department department) throws PersistenceException {
        String insertTableSQL = "INSERT INTO DEPARTMENT" + "(departmentName) VALUES" + "(?)";
        jdbcTemplate1.update(insertTableSQL, new Object[]{department.getDepartmentName()});
    }

    @Override
    public void update(Department department) {
        String updateTableSQL = "UPDATE DEPARTMENT set departmentName=? WHERE departmentId=?";
        jdbcTemplate1.update(updateTableSQL, new Object[]{ department.getDepartmentName(), department.getDepartmentId()});

    }

    @Override
    public void delete(Integer depId) {
        String deleteTableSQL = "DELETE FROM DEPARTMENT WHERE departmentId = ?";
        jdbcTemplate1.update(deleteTableSQL, new Object[]{depId});
    }
}

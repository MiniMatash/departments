package com.minimatash.persistence.impl;

import com.minimatash.entities.Employee;
import com.minimatash.others.EmployeeRowMapper;
import com.minimatash.others.EmployeeSearcher;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.EmployeePersistence;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class EmployeePersistenceImpl implements EmployeePersistence{

    @Override
    public List<Employee> findAll() throws PersistenceException {
        String selectTableSQL = "SELECT * from EMPLOYEE";
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");

        List<Employee> employees = new ArrayList<>();
        List<Map<String,Object>> rows = jdbcTemplate1.queryForList(selectTableSQL);
        EmployeeSearcher employeeSearcher = new EmployeeSearcher();
        employeeSearcher.search(rows,employees);
        return employees;
    }

    @Override
    public Employee findOne(Integer searchId) throws PersistenceException {
        String selectTableSQL = "SELECT empID, depID, firstName,lastName, dateOfBirth, validityOfContract from EMPLOYEE WHERE empID=?";

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");
        Employee employee = (Employee)jdbcTemplate1.queryForObject(
                selectTableSQL, new Object[] { searchId }, new EmployeeRowMapper());
        return employee;
    }

    @Override
    public void create(Employee employee) throws PersistenceException {
        String insertTableSQL = "INSERT INTO EMPLOYEE" + "(depID, firstName, lastName, dateOfBirth,validityOfContract) VALUES" + "(?,?,?,?,?)";
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcTemplate1.update(insertTableSQL, new Object[]{ employee.getDepartmentId(), employee.getFirstName(), employee.getLastName(),
        employee.getDateOfBirth(), employee.getValidityOfContract()});
    }

    @Override
    public void update(Integer empId, Employee employee) {
        String updateTableSQL = "UPDATE EMPLOYEE set depID=?, firstName=?, lastName=?, dateOfBirth=?, validityOfContract=? WHERE empID=?";
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcTemplate1.update(updateTableSQL, new Object[]{ employee.getDepartmentId(), employee.getFirstName(),
                employee.getLastName(),employee.getDateOfBirth(), employee.getValidityOfContract(), empId});
    }

    @Override
    public void delete(Integer id) {
        String deleteTableSQL = "DELETE FROM EMPLOYEE WHERE empID = ?";
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate1 = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcTemplate1.update(deleteTableSQL, new Object[]{id});
    }
}

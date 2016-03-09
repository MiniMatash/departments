package com.minimatash.persistence.impl;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.EmployeePersistence;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeHibernatePersistenceImpl implements EmployeePersistence{

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public List<Employee> findAll() throws PersistenceException {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<>();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(Employee.class);

            employees = crit.list();
        }   catch (Exception e){
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return employees;

    }

    @Override
    public Employee findOne(Integer searchId) throws PersistenceException {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<>();
        try {
//            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(Employee.class)
                .add(Restrictions.eq("employeeId", searchId));
            employees = crit.list();
        }   catch (Exception e){
            logger.error(e.getMessage(), e);
        } finally {
        session.close();
        }
        Employee employee = employees.get(0);
        return employee;
    }


    @Override
    public void create(Employee employee) throws PersistenceException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(employee);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(employee);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = null;
        try {
            employee = findOne(id);
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            session.delete(employee);
            tx.commit();
        } finally {
            session.close();
        }
    }
}

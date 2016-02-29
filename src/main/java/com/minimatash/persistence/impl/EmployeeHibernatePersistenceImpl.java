package com.minimatash.persistence.impl;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.EmployeePersistence;
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

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    static SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

    @Override
    public List<Employee> findAll() throws PersistenceException {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<>();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(Employee.class);

            employees = crit.list();
        }   catch (Exception e){
            System.out.println(e);
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
            System.out.println(e);
        }
        Employee employee = employees.get(0);
        return employee;
    }


    @Override
    public void create(Employee employee) throws PersistenceException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = null;
        try {
            employee = findOne(id);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        session.delete(employee);
        tx.commit();
        session.close();
    }
}

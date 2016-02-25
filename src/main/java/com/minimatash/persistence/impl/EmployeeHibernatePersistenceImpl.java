package com.minimatash.persistence.impl;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.EmployeePersistence;
import com.minimatash.service.EmployeeService;
import com.minimatash.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeHibernatePersistenceImpl implements EmployeePersistence{


    @Override
    public List<Employee> findAll() throws PersistenceException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<>();
        try {
//            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(Employee.class);

            employees = crit.list();
        }   catch (Exception e){
            System.out.println(e);
        }
        //Employee employee = (Employee)session.get(Employee.class, (long)1);

        return employees;
    }

    @Override
    public Employee findOne(Integer search_Id) throws PersistenceException {
        return null;
    }

    @Override
    public void create(Employee employee) throws PersistenceException {

    }

    @Override
    public void update(Integer empid, Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }
}

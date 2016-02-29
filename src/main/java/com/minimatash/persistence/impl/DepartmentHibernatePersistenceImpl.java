package com.minimatash.persistence.impl;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.DepartmentPersistence;
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
public class DepartmentHibernatePersistenceImpl implements DepartmentPersistence {

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    static SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

    @Override
    public List<Department> findAll() throws PersistenceException {
        Session session = sessionFactory.openSession();
        List<Department> departments = new ArrayList<>();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(Department.class);

            departments = crit.list();
        }   catch (Exception e){
            System.out.println(e);
        }
        return departments;
    }

    @Override
    public Department findOne(Integer searchId) throws PersistenceException {
        Session session = sessionFactory.openSession();
        List<Department> departments = new ArrayList<>();
        try {
//            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(Department.class)
                    .add(Restrictions.eq("departmentId", searchId));
            departments = crit.list();
        }   catch (Exception e){
            System.out.println(e);
        }
        Department department = departments.get(0);
        return department;
    }

    @Override
    public void create(Department dept) throws PersistenceException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(dept);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Department dep) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(dep);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Integer deptId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Department department = null;
        try {
            department = findOne(deptId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        session.delete(department);
        tx.commit();
        session.close();
    }
}

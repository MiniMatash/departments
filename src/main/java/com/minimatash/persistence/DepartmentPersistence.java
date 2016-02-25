package com.minimatash.persistence;

import com.minimatash.entities.Department;

import com.minimatash.exceptions.PersistenceException;
import java.util.List;


public interface DepartmentPersistence {
    public List<Department> findAll()throws PersistenceException;
    public Department findOne(Integer searchId) throws PersistenceException;
    public void create(Department dept)throws PersistenceException;
    public void update(Integer dept_id, Department dep);
    public void delete(Integer dept_id);
}

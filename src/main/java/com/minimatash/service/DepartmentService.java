package com.minimatash.service;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.ServiceException;

import java.util.List;

public interface DepartmentService {
        public List<Department> findAll()throws ServiceException;
        public Department findOne(Integer searchId) throws ServiceException;
        public void create(Department dept)throws ServiceException;
        public void update(Integer dept_id, Department dep);
        public void delete(Integer dept_id);
}


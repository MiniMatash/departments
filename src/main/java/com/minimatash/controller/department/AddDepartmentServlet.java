package com.minimatash.controller.department;

import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.impl.DepartmentPersistenceImpl;
import com.minimatash.entities.Department;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("jsp/department/addDepartment.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response){
        String depName =  request.getParameter("departmentName");
        Department dep = new Department(depName);
        DepartmentPersistenceImpl createMethod = new DepartmentPersistenceImpl();
        try {
            createMethod.create(dep);
        }
        catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}


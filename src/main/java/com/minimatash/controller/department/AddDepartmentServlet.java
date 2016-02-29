package com.minimatash.controller.department;

import com.minimatash.exceptions.ServiceException;
import com.minimatash.entities.Department;
import com.minimatash.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addDepartment.html"})
public class AddDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

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
        String departmentName =  request.getParameter("departmentName");
        Department dep = new Department(departmentName);
        try {
            departmentService.create(dep);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}


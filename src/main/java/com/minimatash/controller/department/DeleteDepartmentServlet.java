package com.minimatash.controller.department;

import com.minimatash.service.DepartmentService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet(urlPatterns = {"/deleteDepartment.html"})
public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer departmentID=Integer.parseInt(pArray[1]);
        departmentService.delete(departmentID);
    }
}
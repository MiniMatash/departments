package com.minimatash.controller.employee;

import com.minimatash.service.EmployeeService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet(urlPatterns = {"/deleteEmployee.html"})
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer employeeID=Integer.parseInt(pArray[1]);
        employeeService.delete(employeeID);
    }
}
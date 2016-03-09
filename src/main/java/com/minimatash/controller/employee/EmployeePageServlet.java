package com.minimatash.controller.employee;

import com.google.gson.Gson;
import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(urlPatterns = {"/employeePage.html"})
public class EmployeePageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());

    public EmployeePageServlet() {
        super();
    }

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAjax = Boolean.parseBoolean(request.getParameter("isAjax"));
        if (isAjax) {
            Gson gson = new Gson();
            List<Employee> employeeInfo = new ArrayList<>();
            try {
                employeeInfo = employeeService.findAll();
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            String employeeObj = gson.toJson(employeeInfo);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(employeeObj);
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/employeePage.jsp").forward(request,response);
        }
    }
}

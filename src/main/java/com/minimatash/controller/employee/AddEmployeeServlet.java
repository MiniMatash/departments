package com.minimatash.controller.employee;


import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.EmployeeService;
import com.minimatash.service.impl.EmployeeServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/addEmployee.html"})
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("jsp/employee/addEmployee.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response){
        String fname =  request.getParameter("firstName");
        String lname =  request.getParameter("lastName");
        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth=null;
        try {
            dateOfBirth =  aDate.parse(request.getParameter("DateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int departmentId = Integer.parseInt(request.getParameter("department_ID"));
        int validityOfContract = Integer.parseInt(request.getParameter("validityOfContract"));
        Employee emp = new Employee(fname,lname,departmentId,dateOfBirth,validityOfContract);
        try {
            employeeService.create(emp);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

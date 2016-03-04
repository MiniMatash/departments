package com.minimatash.controller.employee;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet(urlPatterns = {"/updateEmployee.html"})
public class UpdateEmployeeServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private int employeeID;

    public UpdateEmployeeServlet() {
        super();
    }

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        employeeID=Integer.parseInt(pArray[1]);
        Employee employeeInfo = getInfo(employeeID);
        request.setAttribute("employeeInfo",employeeInfo);
        request.getRequestDispatcher("jsp/employee/updateEmployee.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname =  request.getParameter("firstName");
        String lname =  request.getParameter("lastName");
        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth=null;
        try {
            dateOfBirth =  aDate.parse(request.getParameter("DateOfBirth"));
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        int dep_ID = Integer.parseInt(request.getParameter("department_ID"));
        int validityOfContract = Integer.parseInt(request.getParameter("validityOfContract"));
        Employee emp = new Employee(fname,lname,dep_ID,dateOfBirth,validityOfContract);
        emp.setEmployeeId(employeeID);
        employeeService.update(emp);
    }

    private Employee getInfo(int employeeID) {

        Employee employee = new Employee();
        try {
            employee = employeeService.findOne(employeeID);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return employee;
    }
}


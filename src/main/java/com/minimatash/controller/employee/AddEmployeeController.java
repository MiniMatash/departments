package com.minimatash.controller.employee;

import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/addEmployee")
public class AddEmployeeController {
    private Logger logger = Logger.getLogger(this.getClass());

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("WEB-INF/jsp/addEmployee.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    protected  void doPost(HttpServletRequest request, HttpServletResponse response){
        String fname =  request.getParameter("firstName");
        String lname =  request.getParameter("lastName");
        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth=null;
        try {
            dateOfBirth =  aDate.parse(request.getParameter("DateOfBirth"));
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
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

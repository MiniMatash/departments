package com.minimatash.controller;


import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    private Logger logger = Logger.getLogger(this.getClass());
    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
    private int employeeID;

    @RequestMapping(value="/employeePage",method = RequestMethod.GET)
    @ResponseBody
    protected void employeePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @RequestMapping(value="/addEmployee",method = RequestMethod.GET)
    @ResponseBody
    protected void addEmployeeGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("WEB-INF/jsp/addEmployee.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value="/addEmployee",method = RequestMethod.POST)
    protected  void AddEmployeePost(HttpServletRequest request, HttpServletResponse response){
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

    @RequestMapping(value="/deleteEmployee",method = RequestMethod.GET)
    protected void deleteEmployeeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray = adress.split("=");
        Integer employeeID = Integer.parseInt(pArray[1]);
        employeeService.delete(employeeID);
    }

    @RequestMapping(value="/updateEmployee",method = RequestMethod.GET)
    @ResponseBody
    protected void updateEmployeeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        employeeID=Integer.parseInt(pArray[1]);
        Employee employeeInfo = getInfo(employeeID);
        request.setAttribute("employeeInfo",employeeInfo);
        request.getRequestDispatcher("WEB-INF/jsp/updateEmployee.jsp").forward(request,response);
    }

    @RequestMapping(value="/updateEmployee",method = RequestMethod.POST)
    protected void updateEmployeePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

package com.minimatash.controller;


import com.minimatash.entities.Employee;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    private Logger logger = Logger.getLogger(this.getClass());
    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @RequestMapping(value="/employeeList",method = RequestMethod.GET)
    @ResponseBody
    protected List<Employee> employeeList() throws ServletException, IOException, ServiceException {
        List<Employee> employeeInfo = new ArrayList<>();
        employeeInfo = employeeService.findAll();
        return employeeInfo;
    }

    @RequestMapping(value="/employeePage",method = RequestMethod.GET)
    protected ModelAndView employeePage(){
        return new ModelAndView("employeePage");
    }

    @RequestMapping(value="/addEmployee",method = RequestMethod.GET)
    protected ModelAndView addEmployeeGet(){
        return new ModelAndView("addEmployee");
    }

    @RequestMapping(value="/addEmployee",method = RequestMethod.POST)
    protected  void AddEmployeePost(
    @RequestParam(value = "firstName") String fname,
    @RequestParam(value = "lastName") String lname,
    @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth ,
    @RequestParam(value = "departmentID") Integer departmentID,
    @RequestParam(value = "validityOfContract") Integer validityOfContract
    ){
        try {
            Employee emp = new Employee(fname,lname,departmentID,dateOfBirth,validityOfContract);
            employeeService.create(emp);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value="/deleteEmployee",method = RequestMethod.POST)
    protected void deleteEmployeeGet(@RequestParam(value = "employeeId") Integer employeeID) throws ServletException, IOException {
        employeeService.delete(employeeID);
    }

    @RequestMapping(value="/updateEmployee",method = RequestMethod.GET)
    @ResponseBody
    protected ModelAndView updateEmployeeGet(@RequestParam(value = "userId") int empID) throws ServletException, IOException, ServiceException {
        Employee employeeInfo = employeeService.findOne(empID);
        ModelAndView modelAndView = new ModelAndView("updateEmployee");
        modelAndView.addObject("employeeInfo",employeeInfo);
        return modelAndView;
    }

    @RequestMapping(value="/updateEmployee",method = RequestMethod.POST)
    protected void updateEmployeePost(
            @RequestParam(value = "firstName") String fname,
            @RequestParam(value = "lastName") String lname,
            @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth ,
            @RequestParam(value = "departmentID") Integer departmentID,
            @RequestParam(value = "validityOfContract") Integer validityOfContract,
            @RequestParam(value = "employeeID") Integer employeeID
    ) throws ServletException, IOException {
        Employee emp = new Employee(fname,lname,departmentID,dateOfBirth,validityOfContract);
        emp.setEmployeeId(employeeID);
        employeeService.update(emp);
    }

}

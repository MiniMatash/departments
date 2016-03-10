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
@RequestMapping("/updateEmployee")
public class UpdateEmployeeController {
    private Logger logger = Logger.getLogger(this.getClass());
    private int employeeID;

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        employeeID=Integer.parseInt(pArray[1]);
        Employee employeeInfo = getInfo(employeeID);
        request.setAttribute("employeeInfo",employeeInfo);
        request.getRequestDispatcher("WEB-INF/jsp/updateEmployee.jsp").forward(request,response);
    }

    @RequestMapping(method = RequestMethod.POST)
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

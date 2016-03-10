package com.minimatash.controller.employee;

import com.minimatash.service.EmployeeService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/deleteEmployee")
public class DeleteEmployeeController {

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer employeeID=Integer.parseInt(pArray[1]);
        employeeService.delete(employeeID);
    }
}

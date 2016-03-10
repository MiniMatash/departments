package com.minimatash.controller.department;

import com.minimatash.service.DepartmentService;
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
@RequestMapping("/deleteDepartment")
public class DeleteDepartmentController {

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer departmentID=Integer.parseInt(pArray[1]);
        departmentService.delete(departmentID);
    }
}

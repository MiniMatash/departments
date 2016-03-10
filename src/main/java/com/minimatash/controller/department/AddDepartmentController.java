package com.minimatash.controller.department;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.DepartmentService;
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

@Controller
@RequestMapping("/addDepartment")
public class AddDepartmentController {
    private Logger logger = Logger.getLogger(this.getClass());

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("WEB-INF/jsp/addDepartment.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    protected  void doPost(HttpServletRequest request, HttpServletResponse response){
        String departmentName =  request.getParameter("departmentName");
        Department dep = new Department(departmentName);
        try {
            departmentService.create(dep);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

package com.minimatash.controller.department;


import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departmentPage")
public class DepartmentPageController {
    private Logger logger = Logger.getLogger(this.getClass());

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAjax = Boolean.parseBoolean(request.getParameter("isAjax"));
        if (isAjax) {
            Gson gson = new Gson();
            List<Department> departmentInfo = new ArrayList<>();
            try {
                departmentInfo = departmentService.findAll();
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
            String departmentObj = gson.toJson(departmentInfo);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(departmentObj);
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/departmentPage.jsp").forward(request,response);
        }
    }
}

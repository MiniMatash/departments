package com.minimatash.servlet.department;

import com.google.gson.Gson;
import com.minimatash.entities.Department;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(urlPatterns = {"/departmentPage.html"})
public class DepartmentPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

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

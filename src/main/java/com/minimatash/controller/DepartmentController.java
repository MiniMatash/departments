package com.minimatash.controller;

import com.google.gson.Gson;
import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.persistence.impl.DepartmentPersistenceImpl;
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
public class DepartmentController {

    private Logger logger = Logger.getLogger(this.getClass());
    private int departmentID;
    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(value = "/departmentPage",method = RequestMethod.GET)
    @ResponseBody
    protected void departmentPageGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAjax = Boolean.parseBoolean(request.getParameter("isAjax"));
        if (isAjax) {
            Gson gson = new Gson();
            List<Department> departmentInfo = new ArrayList<>();
            try {
                departmentInfo = departmentService.findAll();
                String departmentObj = gson.toJson(departmentInfo);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(departmentObj);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/departmentPage.jsp").forward(request,response);
        }
    }

    @RequestMapping(value = "addDepartment",method = RequestMethod.GET)
    @ResponseBody
    protected void addDepartmentGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("WEB-INF/jsp/addDepartment.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/addDepartment",method = RequestMethod.POST)
    protected  void addDepartmentPost(HttpServletRequest request, HttpServletResponse response){
        String departmentName =  request.getParameter("departmentName");
        Department dep = new Department(departmentName);
        try {
            departmentService.create(dep);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/deleteDepartment",method = RequestMethod.GET)
    protected void deleteDepartmentGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer departmentID=Integer.parseInt(pArray[1]);
        departmentService.delete(departmentID);
    }

    @RequestMapping(value = "/updateDepartment",method = RequestMethod.GET)
    @ResponseBody
    protected void updateEmployeeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        departmentID=Integer.parseInt(pArray[1]);
        Department departmentInfo = getInfo(departmentID);
        request.setAttribute("departmentInfo",departmentInfo);
        request.getRequestDispatcher("WEB-INF/jsp/updateDepartment.jsp").forward(request,response);
    }

    @RequestMapping(value = "/updateDepartment",method = RequestMethod.POST)
    protected void updateDepartmentPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depName =  request.getParameter("departmentName");
        Department department = new Department(depName);
        department.setDepartmentId(departmentID);
        departmentService.update(department);
    }

    private Department getInfo(int departmentID) {
        Department department = new Department();
        DepartmentPersistenceImpl findMethod = new DepartmentPersistenceImpl();
        try {
            department = findMethod.findOne(departmentID);
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
        }
        return department;
    }
}

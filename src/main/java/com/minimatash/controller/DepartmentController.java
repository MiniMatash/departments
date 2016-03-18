package com.minimatash.controller;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.ServiceException;
import com.minimatash.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    private Logger logger = Logger.getLogger(this.getClass());
    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(value = "/departmentList",method = RequestMethod.GET)
    @ResponseBody
    protected List<Department> departmentListGet() throws ServletException, IOException, ServiceException {
        List<Department> departmentInfo = new ArrayList<>();
        departmentInfo = departmentService.findAll();
        return departmentInfo;
    }

    @RequestMapping(value = "/departmentPage",method = RequestMethod.GET)
    protected ModelAndView departmentPageGet() {
        return new ModelAndView("departmentPage");
    }

    @RequestMapping(value = "addDepartment",method = RequestMethod.GET)
    @ResponseBody
    protected ModelAndView addDepartmentGet() {
        return new ModelAndView("addDepartment");
    }

    @RequestMapping(value = "/addDepartment",method = RequestMethod.POST)
    protected  void addDepartmentPost(@RequestParam(value = "departmentName") String departmentName){
        Department dep = new Department(departmentName);
        try {
            departmentService.create(dep);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/deleteDepartment",method = RequestMethod.GET)
    protected void deleteDepartmentGet(@RequestParam(value = "departmentID") Integer departmentID) throws ServletException, IOException {
        departmentService.delete(departmentID);
    }

    @RequestMapping(value = "/updateDepartment",method = RequestMethod.GET)
    @ResponseBody
    protected ModelAndView updateEmployeeGet(@RequestParam(value = "departmentId") Integer departmentID) throws ServletException, IOException, ServiceException {
        Department departmentInfo = departmentService.findOne(departmentID);
        ModelAndView modelAndView = new ModelAndView("updateDepartment");
        modelAndView.addObject("departmentInfo",departmentInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/updateDepartment",method = RequestMethod.POST)
    protected void updateDepartmentPost(
            @RequestParam(value = "departmentName") String departmentName,
            @RequestParam(value = "departmentID") Integer departmentID
            ) throws ServletException, IOException {
        Department department = new Department(departmentName);
        department.setDepartmentId(departmentID);
        departmentService.update(department);
    }

}

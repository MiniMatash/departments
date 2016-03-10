package com.minimatash.controller.department;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
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

@Controller
@RequestMapping("/updateDepartment")
public class UpdateDepartmentController {
    private Logger logger = Logger.getLogger(this.getClass());
    private int departmentID;

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        departmentID=Integer.parseInt(pArray[1]);
        Department departmentInfo = getInfo(departmentID);
        request.setAttribute("departmentInfo",departmentInfo);
        request.getRequestDispatcher("WEB-INF/jsp/updateDepartment.jsp").forward(request,response);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

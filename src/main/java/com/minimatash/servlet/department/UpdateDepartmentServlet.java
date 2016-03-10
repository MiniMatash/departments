package com.minimatash.servlet.department;

import com.minimatash.entities.Department;
import com.minimatash.exceptions.PersistenceException;
import com.minimatash.persistence.impl.DepartmentPersistenceImpl;
import com.minimatash.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = {"/updateDepartment.html"})
public class UpdateDepartmentServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private int departmentID;

    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
    static DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

    public UpdateDepartmentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        departmentID=Integer.parseInt(pArray[1]);
        Department departmentInfo = getInfo(departmentID);
        request.setAttribute("departmentInfo",departmentInfo);
        request.getRequestDispatcher("WEB-INF/jsp/updateDepartment.jsp").forward(request,response);
    }

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


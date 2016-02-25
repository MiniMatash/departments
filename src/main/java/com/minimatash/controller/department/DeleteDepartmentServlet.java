package com.minimatash.controller.department;

import com.minimatash.persistence.impl.DepartmentPersistenceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String HTML_START = "<html><body>";
    public static final String HTML_END = "</body></html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adress = request.getQueryString();
        String[] pArray= adress.split("=");
        Integer employeeID=Integer.parseInt(pArray[1]);
        DepartmentPersistenceImpl deleteMethod = new DepartmentPersistenceImpl();
        deleteMethod.delete(employeeID);
    }
}
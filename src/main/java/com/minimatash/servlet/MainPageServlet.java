package com.minimatash.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = {"/mainPage.html"})
public class MainPageServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(this.getClass());
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            request.getRequestDispatcher("WEB-INF/jsp/mainPage.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
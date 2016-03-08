package com.minimatash.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/mainPage.html"})
public class MainPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            request.getRequestDispatcher("jsp/mainPage.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
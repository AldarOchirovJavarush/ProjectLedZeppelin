package com.javarush.ochirov.controller;

import com.javarush.ochirov.model.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"", "/home"})
public class HomeController extends HttpServlet {
    private static final String USER_ATTR = "user";
    private static final String SIGNIN_PATH = "/auth/signin";
    private static final String HOME_VIEW = "/WEB-INF/view/home.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var user = (User) req.getSession().getAttribute(USER_ATTR);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + SIGNIN_PATH);
            return;
        }

        req.setAttribute(USER_ATTR, user);
        req.getRequestDispatcher(HOME_VIEW).forward(req, resp);
    }
}

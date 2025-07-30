package com.javarush.ochirov.controller;

import com.javarush.ochirov.model.User;
import com.javarush.ochirov.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var path = req.getPathInfo();
        switch (path) {
            case "/signin":
                showSignIn(req, resp);
                break;
            case "/signup":
                showSignUp(req, resp);
                break;
            case "/signout":
                handleSignOut(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var path = req.getPathInfo();
        switch (path) {
            case "/signin":
                handleSignIn(req, resp);
                break;
            case "/signup":
                handleSignUp(req, resp);
                break;
        }
    }

    private void showSignIn(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/auth/signin.jsp").forward(req, resp);
    }

    private void showSignUp(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/auth/signup.jsp").forward(req, resp);
    }

    private void handleSignIn(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> user = authService.login(username, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/auth/signin?error=Invalid username or password");
        }
    }

    private void handleSignUp(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User user = new User();
        user.setName(req.getParameter("username"));

        boolean isRegistered = authService.register(user, req.getParameter("password"));
        if (isRegistered) {
            resp.sendRedirect(req.getContextPath() + "/auth/signin?success=Registration successful. Please login.");
        } else {
            resp.sendRedirect(req.getContextPath() + "/auth/signup?error=Username already exists");
        }
    }

    private void handleSignOut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/auth/signin");
    }
}
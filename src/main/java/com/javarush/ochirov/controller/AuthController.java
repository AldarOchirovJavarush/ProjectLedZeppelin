package com.javarush.ochirov.controller;

import com.javarush.ochirov.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {
    private static final String PATH_AUTH = "/auth";
    private static final String PATH_SIGNIN = "/signin";
    private static final String PATH_SIGNUP = "/signup";
    private static final String PATH_SIGNOUT = "/signout";

    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER = "user";
    private static final String REDIRECT_HOME = "/home";
    private static final String VIEW_SIGNIN = "/WEB-INF/view/auth/signin.jsp";
    private static final String VIEW_SIGNUP = "/WEB-INF/view/auth/signup.jsp";

    private final AuthService authService = new AuthService();

    private final Map<String, Handler> getHandlers = Map.of(
            PATH_SIGNIN, this::showSignIn,
            PATH_SIGNUP, this::showSignUp
    );

    private final Map<String, Handler> postHandlers = Map.of(
            PATH_SIGNIN, this::handleSignIn,
            PATH_SIGNUP, this::handleSignUp,
            PATH_SIGNOUT, this::handleSignOut
    );

    @FunctionalInterface
    private interface Handler {
        void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest(req, resp, getHandlers);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        handleRequest(req, resp, postHandlers);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, Map<String, Handler> handlers)
            throws ServletException, IOException {
        var path = req.getPathInfo();
        var handler = handlers.get(path);

        if (handler != null) {
            handler.handle(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Auth endpoint not found: " + path);
        }
    }

    private void showSignIn(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_SIGNIN).forward(req, resp);
    }

    private void showSignUp(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_SIGNUP).forward(req, resp);
    }

    private void handleSignIn(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var credentials = getCredentials(req);
        var authResult = authService.login(credentials.username, credentials.password);
        var user = authResult.user();
        if (user.isPresent()) {
            req.getSession().setAttribute(ATTR_USER, user.get());
            redirect(resp, req.getContextPath() + REDIRECT_HOME, "");
        } else {
            redirectWithError(resp, PATH_SIGNIN, authResult.error());
        }
    }

    private void handleSignUp(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var credentials = getCredentials(req);
        var authResult = authService.register(credentials.username, credentials.password);
        if (authResult.user().isPresent()) {
            redirect(resp, PATH_AUTH + PATH_SIGNIN, "?success=Registration successful. Please login.");
        } else {
            redirectWithError(resp, PATH_SIGNUP, authResult.error());
        }
    }

    private void handleSignOut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().invalidate();
        redirect(resp, req.getContextPath() + PATH_AUTH + PATH_SIGNIN, "");
    }

    private Credentials getCredentials(HttpServletRequest req) {
        return new Credentials(req.getParameter(PARAM_USERNAME), req.getParameter(PARAM_PASSWORD));
    }

    private record Credentials(String username, String password) {}

    private void redirect(HttpServletResponse resp, String path, String message) throws IOException {
        resp.sendRedirect(resp.encodeRedirectURL(path + message));
    }

    private void redirectWithError(HttpServletResponse resp, String path, String error) throws IOException {
        resp.sendRedirect(resp.encodeRedirectURL(path + "?error=" + URLEncoder.encode(error, StandardCharsets.UTF_8)));
    }
}
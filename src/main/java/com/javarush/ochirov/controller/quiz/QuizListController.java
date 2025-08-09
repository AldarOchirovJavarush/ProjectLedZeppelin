package com.javarush.ochirov.controller.quiz;

import com.javarush.ochirov.service.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/quiz/list")
public class QuizListController extends HttpServlet {
    private QuizService quizService;

    @Override
    public void init() throws ServletException {
        try {
            this.quizService = new QuizService(getServletContext());
        } catch (IOException e) {
            throw new ServletException("Failed to initialize QuizService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("quizzes", quizService.getAvailableQuizzesWithTitles());
        req.getRequestDispatcher("/WEB-INF/view/quiz/list.jsp").forward(req, resp);
    }
}

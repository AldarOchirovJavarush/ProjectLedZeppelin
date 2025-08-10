package com.javarush.ochirov.controller.quiz;

import com.javarush.ochirov.service.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/quiz/play")
public class QuizController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
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
        var quizId = req.getParameter("id");
        logger.info("Requested quiz ID: {}", quizId);
        if (quizId == null || quizId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quiz ID parameter is missing");
            return;
        }
        var config = quizService.getQuizConfig(quizId);
        if (config == null) {
            logger.error("Quiz config not found for ID: {}", quizId);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found");
            return;
        }

        var questionId = req.getParameter("q");
        if (questionId == null || questionId.isEmpty()) {
            questionId = config.getFirstQuestionId();
        }

        if ("end".equals(questionId)) {
            req.getRequestDispatcher("/WEB-INF/view/quiz/complete.jsp").forward(req, resp);
            return;
        }

        var question = quizService.getQuestion(quizId, questionId);
        if (question == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Question not found");
            return;
        }

        req.setAttribute("quizId", quizId);
        req.setAttribute("question", question);
        req.getRequestDispatcher("/WEB-INF/view/quiz/play.jsp").forward(req, resp);
    }
}

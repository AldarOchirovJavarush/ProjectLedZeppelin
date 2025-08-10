package com.javarush.ochirov.service;

import com.javarush.ochirov.model.quiz.QuizConfig;
import com.javarush.ochirov.model.quiz.QuizInfo;
import com.javarush.ochirov.model.quiz.QuizQuestion;
import com.javarush.ochirov.repository.FileRepository;
import com.javarush.ochirov.repository.quiz.QuizRepository;
import jakarta.servlet.ServletContext;
import lombok.Getter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class QuizService {
    private final FileRepository<QuizConfig> quizRepository;
    @Getter
    private final Map<String, QuizConfig> quizzesCache = new ConcurrentHashMap<>();

    public QuizService(ServletContext servletContext) throws IOException {
        this.quizRepository = new QuizRepository(servletContext);
        loadAllQuizzesIntoCache();
    }

    private void loadAllQuizzesIntoCache() throws IOException {
        for (var quizId : quizRepository.getAllIds()) {
            var config = quizRepository.load(quizId);
            quizzesCache.put(quizId, config);
        }
    }

    public List<QuizInfo> getAvailableQuizzesWithTitles() {
        return quizzesCache.entrySet().stream()
                .map(entry -> new QuizInfo(entry.getKey(), entry.getValue().getTitle()))
                .collect(Collectors.toList());
    }

    public QuizQuestion getQuestion(String quizId, String questionId) {
        QuizConfig config = quizzesCache.get(quizId);
        if (config == null || config.getQuestions() == null) {
            return null;
        }
        return config.getQuestions().get(questionId);
    }

    public QuizConfig getQuizConfig(String quizId) {
        return quizzesCache.get(quizId);
    }
}

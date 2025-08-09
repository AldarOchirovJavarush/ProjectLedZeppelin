package com.javarush.ochirov.repository.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.ochirov.model.quiz.QuizConfig;
import com.javarush.ochirov.repository.FileRepository;
import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuizRepository implements FileRepository<QuizConfig> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String quizzesDir;

    public QuizRepository(ServletContext servletContext) {
        this.quizzesDir = servletContext.getRealPath("/WEB-INF/quizzes/");
    }

    @Override
    public QuizConfig load(String quizId) throws IOException {
        var file = new File(quizzesDir + quizId + ".json");
        return mapper.readValue(file, QuizConfig.class);
    }

    @Override
    public void save(String quizId, QuizConfig config) throws IOException {
        var file = new File(quizzesDir + quizId + ".json");
        mapper.writeValue(file, config);
    }

    @Override
    public List<String> getAllIds() {
        return Arrays.stream(Objects.requireNonNull(new File(quizzesDir).list()))
                .filter(name -> name.endsWith(".json"))
                .map(name -> name.replace(".json", ""))
                .collect(Collectors.toList());
    }
}

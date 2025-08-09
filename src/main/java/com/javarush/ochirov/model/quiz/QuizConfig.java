package com.javarush.ochirov.model.quiz;

import lombok.Data;

import java.util.Map;

@Data
public class QuizConfig {
    private String title;
    private String firstQuestionId;
    private Map<String, QuizQuestion> questions;
}

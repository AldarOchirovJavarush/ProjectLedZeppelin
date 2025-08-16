package com.javarush.ochirov.model.quiz;

import lombok.Data;

import java.util.List;

@Data
public class QuizQuestion {
    private String text;
    private List<QuizAnswer> answers;
    private int score;
    private boolean isEnd;
}

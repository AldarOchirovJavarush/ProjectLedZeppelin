package com.javarush.ochirov.model.quiz;

import lombok.Data;

@Data
public class QuizAnswer {
    private String text;
    private String nextId;
    private boolean correct;
}

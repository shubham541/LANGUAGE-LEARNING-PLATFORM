package com.bits.language.resource.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionsDTO {

    private String id;
    private String language;
    private String question;
    private String correctAnswer;
    private List<String> options;
    private boolean isCorrect;

}
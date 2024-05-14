package com.bits.language.resource.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "language_quiz_questions")
@Data
public class Questions {
    @Id
    private String id;
    private String language;
    private String question;
    private String correctAnswer;
    private List<String> options;

}

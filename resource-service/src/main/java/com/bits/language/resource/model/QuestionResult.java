package com.bits.language.resource.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user-quiz-results")
@Data
public class QuestionResult {

    @Id
    private String id;
    private String userName;
    private boolean correct;

}

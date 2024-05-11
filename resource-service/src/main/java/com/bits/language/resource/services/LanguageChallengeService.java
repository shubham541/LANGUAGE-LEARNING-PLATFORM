package com.bits.language.resource.services;

import com.bits.language.resource.dto.QuestionsDTO;

import java.util.List;

public interface LanguageChallengeService {
    List<QuestionsDTO> findByLanguage(String language);

    void submitQuiz(String questions, boolean result);

    List<QuestionsDTO> findByUsername(String username);
}

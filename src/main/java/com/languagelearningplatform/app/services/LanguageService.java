package com.languagelearningplatform.app.services;

import java.util.List;

import com.languagelearningplatform.app.dto.LanguageDTO;

public interface LanguageService {
    List<LanguageDTO> getSupportedLanguages();
    void addLanguage(LanguageDTO languageDTO);
}

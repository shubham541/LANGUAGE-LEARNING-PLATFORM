package com.bits.language.resource.services;

import java.util.List;

import com.bits.language.resource.dto.LanguageDTO;

public interface LanguageService {
    List<LanguageDTO> getSupportedLanguages();
    void addLanguage(LanguageDTO languageDTO);
}

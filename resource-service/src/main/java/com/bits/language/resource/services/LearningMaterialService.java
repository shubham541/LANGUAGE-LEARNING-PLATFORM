package com.bits.language.resource.services;

import com.bits.language.resource.dto.LanguageLearningMaterial;

public interface LearningMaterialService {
    LanguageLearningMaterial getLearningMaterialsByLanguage(String languageId);
}

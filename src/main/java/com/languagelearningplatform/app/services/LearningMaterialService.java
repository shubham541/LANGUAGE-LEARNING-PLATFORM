package com.languagelearningplatform.app.services;

import java.util.List;

import com.languagelearningplatform.app.dto.LearningMaterialDTO;

public interface LearningMaterialService {
    List<LearningMaterialDTO> getLearningMaterialsByLanguage(Long languageId);
}

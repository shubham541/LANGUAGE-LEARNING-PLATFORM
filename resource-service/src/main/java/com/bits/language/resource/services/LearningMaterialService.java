package com.bits.language.resource.services;

import java.util.List;

import com.bits.language.resource.dto.LearningMaterialDTO;

public interface LearningMaterialService {
    List<LearningMaterialDTO> getLearningMaterialsByLanguage(Long languageId);
}

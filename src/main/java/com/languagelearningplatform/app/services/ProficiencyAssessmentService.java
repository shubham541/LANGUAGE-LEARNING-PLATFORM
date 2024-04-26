package com.languagelearningplatform.app.services;

import com.languagelearningplatform.app.dto.AssessmentResultDTO;

public interface ProficiencyAssessmentService {
    void generateProficiencyAssessment(Long languageId);
    void evaluateProficiencyAssessment(AssessmentResultDTO resultDTO);
}

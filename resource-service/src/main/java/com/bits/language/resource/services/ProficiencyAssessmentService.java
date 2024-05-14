package com.bits.language.resource.services;

import com.bits.language.resource.dto.AssessmentResultDTO;

public interface ProficiencyAssessmentService {
    void generateProficiencyAssessment(Long languageId);
    void evaluateProficiencyAssessment(AssessmentResultDTO resultDTO);
}

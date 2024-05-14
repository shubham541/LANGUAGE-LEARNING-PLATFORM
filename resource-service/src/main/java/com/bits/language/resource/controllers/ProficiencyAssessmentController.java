package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.AssessmentResultDTO;
import com.bits.language.resource.services.ProficiencyAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proficiency-assessments")
public class ProficiencyAssessmentController {

    @Autowired
    private ProficiencyAssessmentService proficiencyAssessmentService;

    @GetMapping("/{languageId}")
    public String generateProficiencyAssessment(@PathVariable Long languageId) {
		return null;
        // Call ProficiencyAssessmentService to generate assessment for a specific language
    }

    @PostMapping("/evaluate")
    public String evaluateProficiencyAssessment(@RequestBody AssessmentResultDTO resultDTO) {
		return null;
        // Call ProficiencyAssessmentService to evaluate assessment result
    }
}

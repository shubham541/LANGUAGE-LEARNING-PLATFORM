package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.LanguageLearningMaterial;
import com.bits.language.resource.services.LearningMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/learning-materials")
public class LearningMaterialController {

    @Autowired
    private LearningMaterialService learningMaterialService;

    @GetMapping("/{languageCd}")
    public LanguageLearningMaterial getLearningMaterialsByLanguage(@PathVariable String languageCd) {
		return learningMaterialService.getLearningMaterialsByLanguage(languageCd);
    }
}

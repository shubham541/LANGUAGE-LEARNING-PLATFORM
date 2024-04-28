package com.bits.language.resource.controllers;

import com.bits.language.resource.services.LearningMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/learning-materials")
public class LearningMaterialController {

    @Autowired
    private LearningMaterialService learningMaterialService;

    @GetMapping("/{languageId}")
    public String getLearningMaterialsByLanguage(@PathVariable Long languageId) {
		return null;
        // Call LearningMaterialService to retrieve materials for a specific language
    }
}

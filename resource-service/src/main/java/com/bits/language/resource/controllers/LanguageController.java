package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/supported")
    public String getSupportedLanguages() {
		return null;
        // Call LanguageService to retrieve supported languages
    }

    @PostMapping("/add")
    public String addLanguage(@RequestBody LanguageDTO languageDTO) {
		return null;
        // Call LanguageService to add new language
    }
}
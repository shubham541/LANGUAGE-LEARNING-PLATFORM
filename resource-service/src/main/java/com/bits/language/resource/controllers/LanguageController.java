package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.LanguageDTO;
import com.bits.language.resource.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> getSupportedLanguages() {
		return languageService.getSupportedLanguages();
    }

}

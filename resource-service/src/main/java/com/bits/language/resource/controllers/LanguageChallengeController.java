package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.QuestionsDTO;
import com.bits.language.resource.services.LanguageChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/language-challenges")
public class LanguageChallengeController {

    private final LanguageChallengeService languageChallengeService;

    @GetMapping("/quiz/{language}")
    public List<QuestionsDTO> findByLanguage(@PathVariable String language) {
        return languageChallengeService.findByLanguage(language);
    }

    @PutMapping("/quiz/{id}")
    public void submitQuiz(@PathVariable String id, @RequestParam boolean result) {
        languageChallengeService.submitQuiz(id, result);
    }

    @GetMapping("/quiz/users/{username}")
    public List<QuestionsDTO> findAll(@PathVariable String username) {
        return languageChallengeService.findByUsername(username);
    }

}

package com.languagelearningplatform.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.languagelearningplatform.app.dto.ChallengeDTO;
import com.languagelearningplatform.app.dto.ParticipationDTO;
import com.languagelearningplatform.app.services.LanguageChallengeService;

@Controller
@RequestMapping("/language-challenges")
public class LanguageChallengeController {

    @Autowired
    private LanguageChallengeService languageChallengeService;

    @PostMapping("/create")
    public String createChallenge(@RequestBody ChallengeDTO challengeDTO) {
		return null;
        // Call LanguageChallengeService to create a new challenge
    }

    @PostMapping("/participate")
    public String participateInChallenge(@RequestBody ParticipationDTO participationDTO) {
		return null;
        // Call LanguageChallengeService to participate in a challenge
    }
}

package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.ChallengeDTO;
import com.bits.language.resource.dto.ParticipationDTO;
import com.bits.language.resource.services.LanguageChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

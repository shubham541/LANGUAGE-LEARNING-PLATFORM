package com.languagelearningplatform.app.services;

import com.languagelearningplatform.app.dto.ChallengeDTO;
import com.languagelearningplatform.app.dto.ParticipationDTO;

public interface LanguageChallengeService {
    void createChallenge(ChallengeDTO challengeDTO);
    void participateInChallenge(ParticipationDTO participationDTO);
}

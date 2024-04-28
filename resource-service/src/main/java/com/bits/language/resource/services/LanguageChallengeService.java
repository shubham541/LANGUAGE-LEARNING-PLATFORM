package com.bits.language.resource.services;

import com.bits.language.resource.dto.ChallengeDTO;
import com.bits.language.resource.dto.ParticipationDTO;

public interface LanguageChallengeService {
    void createChallenge(ChallengeDTO challengeDTO);
    void participateInChallenge(ParticipationDTO participationDTO);
}

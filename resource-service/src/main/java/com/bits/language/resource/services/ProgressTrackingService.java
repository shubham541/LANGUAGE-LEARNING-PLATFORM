package com.bits.language.resource.services;

import java.util.List;

import com.bits.language.resource.dto.ProgressDTO;

public interface ProgressTrackingService {
    List<ProgressDTO> getProgressTracking(Long userId, Long languageId);
    void recordProgress(ProgressDTO progressDTO);
}

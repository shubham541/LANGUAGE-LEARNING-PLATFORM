package com.languagelearningplatform.app.services;

import java.util.List;

import com.languagelearningplatform.app.dto.ProgressDTO;

public interface ProgressTrackingService {
    List<ProgressDTO> getProgressTracking(Long userId, Long languageId);
    void recordProgress(ProgressDTO progressDTO);
}

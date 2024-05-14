package com.bits.language.resource.services.impl;

import com.bits.language.resource.dto.ProgressDTO;
import com.bits.language.resource.services.ProficiencyAssessmentService;
import com.bits.language.resource.dto.AssessmentResultDTO;
import com.bits.language.resource.services.ProgressTrackingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProgressTrackingServiceImpl implements ProgressTrackingService {


	@Override
	public List<ProgressDTO> getProgressTracking(Long userId, Long languageId) {
		return null;
	}

	@Override
	public void recordProgress(ProgressDTO progressDTO) {

	}
}

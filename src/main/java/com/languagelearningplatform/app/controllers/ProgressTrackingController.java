package com.languagelearningplatform.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.languagelearningplatform.app.dto.ProgressDTO;
import com.languagelearningplatform.app.services.ProgressTrackingService;

@Controller
@RequestMapping("/progress-tracking")
public class ProgressTrackingController {

    @Autowired
    private ProgressTrackingService progressTrackingService;

    @GetMapping("/{userId}/{languageId}")
    public String getProgressTracking(@PathVariable Long userId, @PathVariable Long languageId) {
		return null;
        // Call ProgressTrackingService to retrieve progress for a user and language
    }

    @PostMapping("/record")
    public String recordProgress(@RequestBody ProgressDTO progressDTO) {
		return null;
        // Call ProgressTrackingService to record user's progress
    }
}

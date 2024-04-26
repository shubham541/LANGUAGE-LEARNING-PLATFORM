package com.languagelearningplatform.app.dto;

public class AssessmentResultDTO {

    private Long assessmentId;
    private int score;

    // Getters and setters

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

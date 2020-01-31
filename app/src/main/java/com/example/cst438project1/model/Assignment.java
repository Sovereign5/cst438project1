package com.example.cst438project1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentID;
    @NonNull
    private String details;
    @NonNull
    private float maxScore;
    @NonNull
    private float earnedScore;
    @NonNull
    private String assignedDate;
    @NonNull
    private String dueDate;
    @NonNull
    private int categoryID;
    @NonNull
    private int courseID;

    public Assignment() {}

    @Ignore
    public Assignment(String details, float maxScore, float earnedScore, String assignedDate,
                      String dueDate, int categoryID, int courseID) {
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryID = categoryID;
        this.courseID = courseID;
    }

    @NonNull
    public String getDetails() {
        return details;
    }

    public void setDetails(@NonNull String details) {
        this.details = details;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }

    public float getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(float earnedScore) {
        this.earnedScore = earnedScore;
    }

    @NonNull
    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(@NonNull String assignedDate) {
        this.assignedDate = assignedDate;
    }

    @NonNull
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(@NonNull String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Assignment\n" +
                "Details: " + details + "\n" +
                "Max score: " + maxScore + "\n" +
                "Earned score: " + earnedScore + "\n" +
                "Assigned date: " + assignedDate + "\n" +
                "Due date: " + dueDate + "\n" +
                "Category ID: " + categoryID + "\n" +
                "Course ID: " + courseID + "\n" +
                "Assignment ID: " + assignmentID;
    }
}

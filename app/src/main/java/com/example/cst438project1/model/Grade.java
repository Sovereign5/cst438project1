package com.example.cst438project1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int gradeID;
    @NonNull
    private char score;
    @NonNull
    private int assignmentID;
    @NonNull
    private int studentID;
    @NonNull
    private int courseID;
    @NonNull
    private String dateEarned;

    public Grade() {}

    @Ignore
    public Grade(char score, int assignmentID, int studentID, int courseID,
                 String dateEarned) {
        this.score = score;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.dateEarned = dateEarned;
    }

    public char getScore() {
        return score;
    }

    public void setScore(char score) {
        this.score = score;
    }

    @NonNull
    public String getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(@NonNull String dateEarned) {
        this.dateEarned = dateEarned;
    }

    @Override
    public String toString() {
        return "Grade \n" +
                "Score: " + score + "\n" +
                "AssignmentID: " + assignmentID + "\n" +
                "StudentID: " + studentID + "\n" +
                "CourseID: " + courseID + "\n" +
                "DateEarned: " + dateEarned + "\n" +
                "GradeID: " + gradeID + "\n";
    }
}

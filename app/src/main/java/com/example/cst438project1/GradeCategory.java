package com.example.cst438project1;


import com.example.cst438project1.DB.CourseCategoryDatabase;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = CourseCategoryDatabase.GRADE_CATEGORY_TABLE)
public class GradeCategory {

    @PrimaryKey(autoGenerate = true)
    private int CategoryId;

    private String title;

    private double weight;

    private int GradeId;

    public GradeCategory(String sTitle, double sWeight, int sGradeId) {
        title = sTitle;
        weight = sWeight;
        GradeId = sGradeId;
    }

    public GradeCategory() {
        title = "Unknown";
        weight = 100.00;
        GradeId = 22;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGradeId() {
        return GradeId;
    }

    public void setGradeId(int gradeId) {
        GradeId = gradeId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}


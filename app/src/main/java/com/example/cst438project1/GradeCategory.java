package com.example.cst438project1;


import com.example.cst438project1.DB.AppDatabase;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeCategory that = (GradeCategory) o;
        return CategoryId == that.CategoryId &&
                Double.compare(that.weight, weight) == 0 &&
                GradeId == that.GradeId &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CategoryId, title, weight, GradeId);
    }
}

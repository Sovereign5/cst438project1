package com.example.cst438project1.DB;


import com.example.cst438project1.GradeCategory;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GradeCategoryDAO {

    @Insert
    void insert(GradeCategory... gradeCategories);

    @Update
    void update(GradeCategory... gradeCategories);

    @Delete
    void delete(GradeCategory gradeCategory);

    @Query("SELECT * FROM " + AppDatabase.GRADE_CATEGORY_TABLE )
    List<GradeCategory> getGradeCategories();

    @Query("DELETE FROM " + AppDatabase.GRADE_CATEGORY_TABLE + " WHERE CategoryId = :categoryID")
    void deleteByCatId(long categoryID);

}

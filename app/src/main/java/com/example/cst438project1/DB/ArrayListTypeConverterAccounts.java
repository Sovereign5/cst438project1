package com.example.cst438project1.DB;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public class ArrayListTypeConverterAccounts {

    Gson gson = new Gson();

    @TypeConverter
    public List<CourseLog> stringToCourseList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<CourseLog>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String CourseListToString(List<CourseLog> someObjects) {
        return gson.toJson(someObjects);
    }
}


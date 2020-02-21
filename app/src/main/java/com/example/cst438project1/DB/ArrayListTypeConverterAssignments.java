package com.example.cst438project1.DB;

import com.example.cst438project1.model.Assignment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public class ArrayListTypeConverterAssignments {

    Gson gson = new Gson();

    @TypeConverter
    public List<Assignment> stringToAssignments(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Assignment>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String AssignmentsToString(List<Assignment> someObjects) {
        return gson.toJson(someObjects);
    }
}
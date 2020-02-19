package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cst438project1.model.AppDatabase;

public class MainActivity extends AppCompatActivity {

    //Button Declarations
    private Button mViewCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDatabase db = AppDatabase.getAppDatabase(getBaseContext());
        db.getAssignmentDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase.getAppDatabase(MainActivity.this).loadData(this);
        // Interface Definitions
        mViewCourses = (Button) findViewById(R.id.courseButton);

        mViewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchViewCourses();
            }
        });
    }

    private void launchViewCourses() {
        Intent intent = new Intent(this, ViewCourse.class);
        startActivity(intent);
    }
}
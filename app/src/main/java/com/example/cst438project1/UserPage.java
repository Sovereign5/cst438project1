package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseLog;

import java.util.List;

public class UserPage extends AppCompatActivity {

    Button addCourseButton;
    TextView greetingView;
    RecyclerView displayCourses;


    private AccountLogDAO accountLogDAO;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Context mContext = getApplicationContext();

        addCourseButton = (Button) findViewById(R.id.addCourseButton);
        greetingView = (TextView) findViewById(R.id.usernameTextView);
        displayCourses = (RecyclerView) findViewById(R.id.userPageRecycler);


        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        AccountLog currUser = accountLogDAO.findAccount(getIntent().getStringExtra("user"),getIntent().getStringExtra("pass"));

        List<CourseLog> currCourses = currUser.getUserCourses();

        greetingView.setText(currUser.getFirstname());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        displayCourses.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new CourseAdapter(currCourses);
        displayCourses.setAdapter(mAdapter);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this, AddCoursePage.class);
                startActivity(intent);
            }
        });


    }




}




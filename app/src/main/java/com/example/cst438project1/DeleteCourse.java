package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;

import java.util.List;

public class DeleteCourse extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    List<CourseLog> allCourses;
    AccountLog currUser;

    RecyclerView delete;
    Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        setUpActivity();

        delete = (RecyclerView) findViewById(R.id.deleteRecycler);
        returnButton = (Button) findViewById(R.id.deleteCourseReturnButton);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        delete.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new DeleteAdapter(allCourses,currUser);
        delete.setAdapter(mAdapter);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteCourse.this,UserPage.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass",currUser.getPassword());
                view.getContext().startActivity(intent);
            }
        });

    }



    public void setUpActivity() {
        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();

        courseDao = courseDatabase.getCourseLogDAO();
        allCourses = courseDao.getCourseLogs();
        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));
    }

}

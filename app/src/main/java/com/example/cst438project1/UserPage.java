package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseLog;

import java.util.List;

public class UserPage extends AppCompatActivity {

    Button addCourseButton;
    Button enrollLink;
    Button deleteCourse;
    Button logoutButton;
    Button editUser;
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
        greetingView = (TextView) findViewById(R.id.usernameTextView1);
        displayCourses = (RecyclerView) findViewById(R.id.userPageRecycler);
        enrollLink = (Button) findViewById(R.id.enrollButtonLink);
        deleteCourse = (Button) findViewById(R.id.deleteCourseButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        editUser = (Button) findViewById(R.id.editUserButton);



        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        final AccountLog currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));

        List<CourseLog> currUserCourses = currUser.getUserCourses();

        greetingView.setText(currUser.getFirstname());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        displayCourses.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new CourseAdapter(currUserCourses,currUser);
        displayCourses.setAdapter(mAdapter);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this, AddCoursePage.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });

        enrollLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this,EnrollActivity.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });

        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this, DeleteCourse.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this,Login.class);
                startActivity(intent);
            }
        });

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this, editAccount.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });




    }

    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }




}




package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;

import java.util.List;

public class AddCoursePage extends AppCompatActivity {


    EditText courseTitle;
    EditText courseInstructor;
    EditText startDate;
    EditText endDate;
    EditText courseDescription;

    Button createCourse;
    Button returnButton;

    private AccountLogDAO accountLogDAO;
    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_page);


        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();

        courseDao = courseDatabase.getCourseLogDAO();

        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        courseTitle = (EditText) findViewById(R.id.addCoursePageCourseTitleEditText);
        courseInstructor = (EditText) findViewById(R.id.addCoursePageInstructor);
        startDate = (EditText) findViewById(R.id.addCourseStartDateEditText);
        endDate = (EditText) findViewById(R.id.addCourseEndDateEditText);
        courseDescription = (EditText) findViewById(R.id.addCourseDescriptionEditText);
        createCourse = (Button) findViewById(R.id.addCourseCreateCourseButton);
        returnButton = (Button) findViewById(R.id.addCourseCancelButton);


        final AccountLog currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));

        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseLog newcourse = new CourseLog(courseInstructor.getText().toString(),courseTitle.getText().toString(),courseDescription.getText().toString(),startDate.getText().toString(),endDate.getText().toString());
                List<CourseLog> courses = courseDao.getCourseLogs();
                boolean flag = true;
                for (CourseLog c : courses) {
                    if(c.getTitle().equals(newcourse.getTitle())) {
                        toastMaker("That course already exists please try again");
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    courseDao.insert(newcourse);
                    toastMaker("Successfully Created Course");
                    Intent intent = new Intent(AddCoursePage.this,UserPage.class);
                    intent.putExtra("username",currUser.getUsername());
                    intent.putExtra("pass", currUser.getPassword());
                    startActivity(intent);
                }

            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCoursePage.this,UserPage.class);
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

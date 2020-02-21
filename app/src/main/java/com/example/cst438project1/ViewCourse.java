package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;
import com.example.cst438project1.model.Assignment;
import com.example.cst438project1.model.AssignmentDAO;
import com.example.cst438project1.model.AssignmentDatabase;
import com.example.cst438project1.model.Grade;
import com.example.cst438project1.model.GradeDAO;

import org.w3c.dom.Text;

import java.util.List;

public class ViewCourse extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    private GradeDAO gradeDAO;


    TextView courseTitleDisplay;
    TextView courseInstructorDisplay;
    TextView courseStartDisplay;
    TextView courseEndDisplay;
    TextView courseDescriptionDisplay;
    TextView currentGradeDisplay;


    Button returnButton;
    Button editCourse;
    Button viewAssignments;

    List<CourseLog> courses;
    List<Assignment> currUserAssignments;

    CourseLog currCourse;
    AccountLog currUser;
    String currCourseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        courseTitleDisplay = (TextView) findViewById(R.id.viewCourseTitle);
        courseInstructorDisplay = (TextView) findViewById(R.id.viewCourseInstructor);
        courseStartDisplay = (TextView) findViewById(R.id.viewCourseStart);
        courseEndDisplay = (TextView) findViewById(R.id.viewCourseEnd);
        courseDescriptionDisplay = (TextView) findViewById(R.id.viewCourseDescription);
        currentGradeDisplay = (TextView) findViewById(R.id.viewCourseCurrentGrade);

        returnButton = (Button) findViewById(R.id.viewCourseReturn);
        editCourse = (Button) findViewById(R.id.viewCourseEditCourse);
        viewAssignments = (Button) findViewById(R.id.viewAssignments);
        setupActivity();


        courseTitleDisplay.setText(currCourse.getTitle());
        courseInstructorDisplay.setText(currCourse.getInstructor());
        courseStartDisplay.setText(currCourse.getStartDate());
        courseEndDisplay.setText(currCourse.getEndDate());
        courseDescriptionDisplay.setText(currCourse.getDescription());

        currUserAssignments = currUser.getUserAssignments();

        double total = 0;
        for(Assignment a : currUserAssignments) {
            if(a.getCourseID() == currCourse.getCourseID()) {
                total += (a.getEarnedScore()/a.getMaxScore());
            }
        }
        char letterGrade = getLetterGrade(total * 100);
        Grade classGrade = new Grade(letterGrade,0,currUser.getAccountId(),currCourse.getCourseID(),"NULL");
        Grade check = gradeDAO.findGrade(currCourse.getCourseID(),currUser.getAccountId());
        if(check != null) {
            check = classGrade;
            gradeDAO.updateGrade(check);
        }
        else {
            gradeDAO.addGrade(classGrade);
        }

        if(total == 0) {
            currentGradeDisplay.setText("N/A");
        } else {
            currentGradeDisplay.setText(String.valueOf(letterGrade));
        }

        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCourse.this, EditCourseActivity.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                intent.putExtra("course",currCourse.getTitle());
                startActivity(intent);
            }
        });

        viewAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCourse.this, AssignmentActivity.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                intent.putExtra("course",currCourse.getTitle());
                startActivity(intent);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCourse.this, UserPage.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                startActivity(intent);
            }
        });
    }



    public void setupActivity() {
        currCourseTitle = getIntent().getStringExtra("courseTitle");

        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();

        courseDao = courseDatabase.getCourseLogDAO();

        courses = courseDao.getCourseLogs();

        gradeDAO = Room.databaseBuilder(this, AssignmentDatabase.class, AssignmentDatabase.GRADE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getGradeDAO();

        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));

        for(CourseLog c : courses) {
            if(c.getTitle().equals(currCourseTitle)) {
                currCourse = c;
                break;
            }
        }
    }

    char getLetterGrade(double total) {
        if(total > 90.00) {
            return 'A';
        }
        else if(total > 80.00) {
            return 'B';
        }
        else if(total > 70.00) {
            return 'C';
        }
        else if(total > 60.00) {
            return 'D';
        }
        return 'F';
    }

}

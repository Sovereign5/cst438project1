package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnrollActivity extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    CourseLog courseToEnroll;

    Button returnButton;
    Button enrollButton;

    Spinner spinner;

    List<CourseLog> courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        returnButton = (Button) findViewById(R.id.returnButton);
        enrollButton = (Button) findViewById(R.id.enrollSubmitButton);
        spinner = (Spinner) findViewById(R.id.courseSpinner);

        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();

        courseDao = courseDatabase.getCourseLogDAO();

        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();

        final AccountLog currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));

        final List<CourseLog> currUserCourses = currUser.getUserCourses();

        courses = courseDao.getCourseLogs();


        List<String> courseNames = new ArrayList<>();
        HashSet<String> checker = new HashSet<>();

        for(CourseLog c : courses) {
            if(!checker.contains(c.getTitle())) {
                courseNames.add(c.getTitle());
                checker.add(c.getTitle());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,courseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCourseTitle = adapterView.getItemAtPosition(i).toString();
                for(CourseLog c: courses) {
                    if(c.getTitle().equals(selectedCourseTitle)) {
                        courseToEnroll = c;
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CourseLog c : currUserCourses) {
                    if(c.getCourseID() == courseToEnroll.getCourseID()) {
                        toastMaker("Already Enrolled In This Course");
                        return;
                    }
                }
                currUser.insertCourse(courseToEnroll);
                accountLogDAO.update(currUser);
                toastMaker("Successfully Enrolled In Course");

            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnrollActivity.this, UserPage.class);
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

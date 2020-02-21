package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;
import com.example.cst438project1.model.AssignmentDAO;
import com.example.cst438project1.model.AssignmentDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class EditCourseActivity extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    private AssignmentDAO assignmentDAO;

    List<CourseLog> courses;
    CourseLog currCourse;
    String currCourseTitle;
    AccountLog currUser;

    TextView currCourseDisplay;

    EditText courseTitleEditText;
    EditText courseInstructorEditText;
    EditText courseStartEditText;
    EditText courseEndEditText;
    EditText courseDescriptionEditText;

    Button returnButton;
    Button confirmButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        setUpActivity();

        currCourseDisplay = (TextView) findViewById(R.id.editCourseTitleDisplay);
        courseTitleEditText = (EditText) findViewById(R.id.editCourseTitleEditText);
        courseInstructorEditText = (EditText) findViewById(R.id.editCourseInstructorEditText);
        courseStartEditText = (EditText) findViewById(R.id.editCourseStartEditText);
        courseEndEditText = (EditText) findViewById(R.id.editCourseEndEditText);
        courseDescriptionEditText = (EditText) findViewById(R.id.editCourseDescriptionEditText);
        returnButton = (Button) findViewById(R.id.editCourseReturnButton);
        confirmButton = (Button) findViewById(R.id.editCourseConfirmButton);

        currCourseDisplay.setText(currCourseTitle);
        courseTitleEditText.setText(currCourse.getTitle());
        courseInstructorEditText.setText(currCourse.getInstructor());
        courseStartEditText.setText(currCourse.getStartDate());
        courseEndEditText.setText(currCourse.getEndDate());
        courseDescriptionEditText.setText(currCourse.getDescription());

        final List<CourseLog> userCourses = currUser.getUserCourses();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currCourse.setTitle(courseTitleEditText.getText().toString());
                currCourse.setInstructor(courseInstructorEditText.getText().toString());
                currCourse.setStartDate(courseStartEditText.getText().toString());
                currCourse.setEndDate(courseEndEditText.getText().toString());
                currCourse.setDescription(courseDescriptionEditText.getText().toString());
                courseDao.update(currCourse);

                for(CourseLog c : userCourses) {
                    if(c.getCourseID() == currCourse.getCourseID()) {
                        userCourses.remove(c);
                        userCourses.add(currCourse);
                        break;
                    }
                }
                accountLogDAO.update(currUser);
                toastMaker("Course Updated");
                Intent intent = new Intent(EditCourseActivity.this,ViewCourse.class);
                intent.putExtra("courseTitle",currCourse.getTitle());
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass",currUser.getPassword());
                view.getContext().startActivity(intent);

            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCourseActivity.this,ViewCourse.class);
                intent.putExtra("courseTitle",currCourse.getTitle());
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass",currUser.getPassword());
                view.getContext().startActivity(intent);
            }
        });


    }

    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }



    public void setUpActivity() {
        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();
        courseDao = courseDatabase.getCourseLogDAO();
        courses = courseDao.getCourseLogs();
        currCourseTitle = getIntent().getStringExtra("course");
        assignmentDAO = Room.databaseBuilder(this, AssignmentDatabase.class, AssignmentDatabase.ASSIGNMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getAssignmentDAO();
        accountLogDAO = Room.databaseBuilder(this, com.example.cst438project1.DB.AppDatabase.class, AppDatabase.dbName)
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

}

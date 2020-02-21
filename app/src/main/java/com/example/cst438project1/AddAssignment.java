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
import com.example.cst438project1.model.Assignment;
import com.example.cst438project1.model.AssignmentDAO;
import com.example.cst438project1.model.AssignmentDAO_Impl;
import com.example.cst438project1.model.AssignmentDatabase;

import java.util.List;

public class AddAssignment extends AppCompatActivity {

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    private AssignmentDAO assignmentDAO;

    List<Assignment> allAssignments;
    List<CourseLog> courses;
    CourseLog currCourse;

    String currCourseTitle;

    AccountLog currUser;

    TextView currCourseDisplay;

    EditText details;
    EditText maxScore;
    EditText earnedScore;
    EditText dateAssigned;
    EditText dateDue;

    Button createNew;
    Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        setUpActivity();

        currCourseDisplay = (TextView) findViewById(R.id.currCourseTitleText);
        details = (EditText) findViewById(R.id.assignmentDetailsEditText);
        maxScore = (EditText) findViewById(R.id.maxScoreEditText);
        earnedScore = (EditText) findViewById(R.id.earnedScoreEditText);
        dateAssigned = (EditText) findViewById(R.id.assignedDateEditText);
        dateDue = (EditText) findViewById(R.id.dueDateEditText);
        createNew = (Button) findViewById(R.id.createAssignmentSubmitButton);
        returnButton = (Button) findViewById(R.id.addAssignmentReturnButton);

        currCourseDisplay.setText(currCourseTitle);





        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Assignment newAssignment = new Assignment(details.getText().toString(),Float.parseFloat(maxScore.getText().toString()),Float.parseFloat(earnedScore.getText().toString()),dateAssigned.getText().toString(),dateDue.getText().toString(),0,currCourse.getCourseID());

                for(Assignment a : allAssignments) {
                    if(a.getAssignmentID() == newAssignment.getAssignmentID() && a.getCourseID() == newAssignment.getCourseID()) {
                        toastMaker("That assignment already exists in this class");
                        return;
                    }
                }
                assignmentDAO.addAssignment(newAssignment);
                currUser.insertAssignment(newAssignment);
                accountLogDAO.update(currUser);
                toastMaker("Assignment Created Successfully");
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAssignment.this, AssignmentActivity.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                intent.putExtra("course",currCourse.getTitle());
                startActivity(intent);
            }
        });

    }



    public void setUpActivity() {
        currCourseTitle = getIntent().getStringExtra("course");
        courseDatabase = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();
        courseDao = courseDatabase.getCourseLogDAO();
        courses = courseDao.getCourseLogs();
        accountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();
        assignmentDAO = Room.databaseBuilder(this, AssignmentDatabase.class, AssignmentDatabase.ASSIGNMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getAssignmentDAO();
        allAssignments = assignmentDAO.getAllAssignments();
        currUser = accountLogDAO.findAccount(getIntent().getStringExtra("username"),getIntent().getStringExtra("pass"));
        for(CourseLog c : courses) {
            if(c.getTitle().equals(currCourseTitle)) {
                currCourse = c;
                break;
            }
        }
    }

    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }


}

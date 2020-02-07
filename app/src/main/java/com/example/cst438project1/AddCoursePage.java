package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddCoursePage extends AppCompatActivity {

    EditText courseTitleEditText;
    EditText courseInstructorEditText;
    EditText courseStartDateEditText;
    EditText courseEndDateEditText;
    EditText courseDescriptionEditText;

    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_page);

        courseTitleEditText = (EditText) findViewById(R.id.courseTitleEditText);
        courseInstructorEditText = (EditText) findViewById(R.id.courseInstructorEditText);
        courseStartDateEditText = (EditText) findViewById(R.id.courseStartDateEditText);
        courseEndDateEditText = (EditText) findViewById(R.id.courseEndDateEditText);
        courseDescriptionEditText = (EditText) findViewById(R.id.courseDescriptionEditText);

        submitButton = (Button) findViewById(R.id.addCourseSubmitButton);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Need to add to check to make sure there is things in these fields.

                String title = courseTitleEditText.getText().toString();
                String teacher = courseInstructorEditText.getText().toString();
                //Date start = courseStartDateEditText.getText();
                String description = courseDescriptionEditText.getText().toString();

                //Just needs to be sent to DB

            }
        });


    }



    private boolean isEmpty(EditText textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }
}

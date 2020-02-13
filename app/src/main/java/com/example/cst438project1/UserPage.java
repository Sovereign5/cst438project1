package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class UserPage extends AppCompatActivity {

    Button addCourseButton;

    ListView courseDisplayLayout;

    TextView userNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        addCourseButton = (Button) findViewById(R.id.addCourseButton);
        courseDisplayLayout = (ListView) findViewById(R.id.courseDisplayListView);
        userNameDisplay = (TextView) findViewById(R.id.usernameTextView);

        //Waiting for DB's to be setup before I can fill this in
        //Also want to set up navigation drawer

        //Redirects to AddCoursePage
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPage.this, AddCoursePage.class);
                startActivity(intent);
            }
        });


    }
}

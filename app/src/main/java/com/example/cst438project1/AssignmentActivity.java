package com.example.cst438project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;
import com.example.cst438project1.model.Assignment;
import com.example.cst438project1.model.AssignmentDAO;
import com.example.cst438project1.model.AssignmentDatabase;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    List<Assignment> assignments;

    private AccountLogDAO accountLogDAO;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;

    private AssignmentDAO assignmentDAO;
    List<Assignment> allAssignments;


    Button returnButton;
    Button addAssignment;

    TextView currentCourseView;

    AlertDialog edit;


    List<CourseLog> courses;
    List<Assignment> userAssignments;

    CourseLog currCourse;
    String currCourseTitle;
    AccountLog currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignment);

//        AssignmentDatabase db = AssignmentDatabase.getAppDatabase(getBaseContext());
//        db.loadData(this);
//        db.getAssignmentDAO();
//        assignments = AssignmentDatabase.getAppDatabase(this).getAssignmentDAO().getAllAssignments();
//        Log.d("AssignmentActivity", "Assignment size: " + assignments.size());

        returnButton = (Button) findViewById(R.id.assignmentReturnButton);
        addAssignment = (Button) findViewById(R.id.addAssignmentButton);
        currentCourseView = (TextView) findViewById(R.id.currentCourseTextView);


        setUpActivity();

        currentCourseView.setText(currCourseTitle);

        userAssignments = currUser.getUserAssignments();
        List<Assignment> courseAssignments = new ArrayList<>();

        for(Assignment a : userAssignments) {
            if(a.getCourseID() == currCourse.getCourseID()) {
                courseAssignments.add(a);
            }
        }




        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        rv.setAdapter( new AssignmentAdapter(courseAssignments) );


        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignmentActivity.this, AddAssignment.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                intent.putExtra("course",currCourse.getTitle());
                startActivity(intent);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignmentActivity.this, ViewCourse.class);
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass", currUser.getPassword());
                intent.putExtra("courseTitle",currCourse.getTitle());
                startActivity(intent);
            }
        });





    }

    private class AssignmentAdapter  extends RecyclerView.Adapter<ItemHolder> {

        List<Assignment> currAssignments;

        public AssignmentAdapter(List<Assignment> userAssign) {
            currAssignments = userAssign;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(AssignmentActivity.this);
            View v = inflater.inflate(R.layout.item,parent,false);
            return new  ItemHolder(v,currAssignments);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            //holder.bind(currAssignments.get(position));
            Assignment assignment = currAssignments.get(position);
            holder.item_id.setText(assignment.toString());

        }

        @Override
        public int getItemCount() { return currAssignments.size(); }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        TextView item_id;

        Button editAssign;
        Button deleteAssign;

        public ItemHolder(View v, final List<Assignment> currA) {
            super(v);
            item_id = v.findViewById(R.id.item_id);
            editAssign = v.findViewById(R.id.editAssignmentButtonItem);
            deleteAssign = v.findViewById(R.id.deleteAssignButton);

            deleteAssign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assignment currPos = currA.get(position);
                    AlertDialog alertDialog = new AlertDialog.Builder(AssignmentActivity.this).create();
                    alertDialog.setTitle("Deleting Assignment");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    for(Assignment a : userAssignments) {
                                        if(a.getAssignmentID() == currPos.getAssignmentID()) {
                                            userAssignments.remove(a);
                                            assignmentDAO.deleteAssignment(a);
                                            break;
                                        }
                                    }
                                    accountLogDAO.update(currUser);
                                    Intent intent = new Intent(AssignmentActivity.this, AssignmentActivity.class);
                                    intent.putExtra("username",currUser.getUsername());
                                    intent.putExtra("pass", currUser.getPassword());
                                    intent.putExtra("course",currCourse.getTitle());
                                    startActivity(intent);

                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    AlertDialog alertDialog1 = alertDialog;
                    alertDialog1.show();
                }
            });

            editAssign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assignment currPos = currA.get(position);
                    final LinearLayout linearLayout = new LinearLayout(AssignmentActivity.this);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    final EditText details = new EditText(AssignmentActivity.this);
                    details.setText(currPos.getDetails());
                    details.setLayoutParams(layoutParams);

                    final EditText maxScore = new EditText(AssignmentActivity.this);
                    maxScore.setText(String.valueOf(currPos.getMaxScore()));
                    maxScore.setInputType(InputType.TYPE_CLASS_NUMBER);
                    maxScore.setLayoutParams(layoutParams);

                    final EditText earnedScore = new EditText(AssignmentActivity.this);
                    earnedScore.setText(String.valueOf(currPos.getEarnedScore()));
                    maxScore.setInputType(InputType.TYPE_CLASS_NUMBER);
                    earnedScore.setLayoutParams(layoutParams);

                    final EditText assignedDate = new EditText(AssignmentActivity.this);
                    assignedDate.setText(currPos.getAssignedDate());
                    assignedDate.setLayoutParams(layoutParams);

                    final EditText dueDate = new EditText(AssignmentActivity.this);
                    dueDate.setText(currPos.getDueDate());
                    dueDate.setLayoutParams(layoutParams);

                    linearLayout.addView(details);
                    linearLayout.addView(maxScore);
                    linearLayout.addView(earnedScore);
                    linearLayout.addView(assignedDate);
                    linearLayout.addView(dueDate);

                    AlertDialog alertDialog = new AlertDialog.Builder(AssignmentActivity.this).create();
                    alertDialog.setTitle("Editing Assignment");
                    alertDialog.setView(linearLayout);

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Submit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String udetails = details.getText().toString();
                                    float umaxscore = Float.parseFloat(maxScore.getText().toString());
                                    float uearnedscore = Float.parseFloat(earnedScore.getText().toString());
                                    String uassigneddate = assignedDate.getText().toString();
                                    String uduedate = dueDate.getText().toString();
                                    Assignment updated = new Assignment(udetails,umaxscore,uearnedscore,uassigneddate,uduedate,0,currCourse.getCourseID());
                                    updated.setAssignmentID(currPos.getAssignmentID());
                                    assignmentDAO.updateAssignment(updated);
                                    for(Assignment a : userAssignments) {
                                        if(a.getAssignmentID() == updated.getAssignmentID()) {
                                            userAssignments.remove(a);
                                            userAssignments.add(updated);
                                            break;
                                        }
                                    }
                                    accountLogDAO.update(currUser);
                                    Intent intent = new Intent(AssignmentActivity.this, AssignmentActivity.class);
                                    intent.putExtra("username",currUser.getUsername());
                                    intent.putExtra("pass", currUser.getPassword());
                                    intent.putExtra("course",currCourse.getTitle());
                                    startActivity(intent);

                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    edit = alertDialog;
                    edit.show();
                }
            });

        }

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
        allAssignments = assignmentDAO.getAllAssignments();

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

package com.example.cst438project1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.service.autofill.Dataset;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438project1.DB.AccountLogDAO;
import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.CourseDAO;
import com.example.cst438project1.DB.CourseDatabase;
import com.example.cst438project1.DB.CourseLog;
import com.example.cst438project1.model.Assignment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.ViewHolder> {
    private List<CourseLog> Dataset;

    private CourseDAO courseDao;
    private CourseDatabase courseDatabase;
    private AccountLogDAO accountLogDAO;

    AccountLog currUser;

    List<AccountLog> allUsers;



    Context context;


    public DeleteAdapter(List<CourseLog> myDataset, AccountLog currUser) {
        Dataset = myDataset;
        this.currUser = currUser;
    }

    @NonNull
    @Override
    public DeleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.delete_item,parent,false);
        return new DeleteAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DeleteAdapter.ViewHolder holder, int position) {

        CourseLog courseLog = Dataset.get(position);
        holder.courseTitle.setText(courseLog.getTitle());
        holder.courseInstructor.setText(courseLog.getInstructor());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }

    public void dbStuff() {
        courseDatabase = Room.databaseBuilder(context, CourseDatabase.class, CourseDatabase.dbName)
                .allowMainThreadQueries()
                .build();
        courseDao = courseDatabase.getCourseLogDAO();
        accountLogDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDAO();
        allUsers = accountLogDAO.getAccountLog();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView courseTitle;
        TextView courseInstructor;
        Button viewCourseButton;


        public ViewHolder(@NonNull View item) {
            super(item);

            courseTitle = item.findViewById(R.id.courseTitleDisplay);
            courseInstructor = item.findViewById(R.id.courseInstructorDisplay);
            viewCourseButton = item.findViewById(R.id.recycleCourseButton);
            dbStuff();

            viewCourseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        final CourseLog courseToDelete = Dataset.get(position);
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Deleting Course");
                        alertDialog.setMessage("You are about to delete " + courseToDelete.getTitle());
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        for(AccountLog a : allUsers) {
                                            List<CourseLog> temp = a.getUserCourses();
                                            temp.remove(courseToDelete);
                                            a.setUserCourses(temp);
                                            accountLogDAO.update(a);
                                        }
                                        courseDao.delete(courseToDelete);
                                        toastMaker("Course Deleted");
                                        Intent intent = new Intent(context, DeleteCourse.class);
                                        intent.putExtra("username",currUser.getUsername());
                                        intent.putExtra("pass", currUser.getPassword());
                                        view.getContext().startActivity(intent);
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        AlertDialog deleter = alertDialog;
                        deleter.show();
                    }
                }
            });

        }



    }

    private void toastMaker(String message){
        Toast t = Toast.makeText(context,message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }

}

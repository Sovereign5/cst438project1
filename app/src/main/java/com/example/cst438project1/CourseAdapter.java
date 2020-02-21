package com.example.cst438project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cst438project1.DB.CourseLog;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<CourseLog> Dataset;
    private AccountLog currUser;

    Context context;


    public CourseAdapter(List<CourseLog> myDataset, AccountLog User) {
        Dataset = myDataset;
        currUser = User;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CourseLog courseLog = Dataset.get(position);
        holder.courseTitle.setText(courseLog.getTitle());
        holder.courseInstructor.setText(courseLog.getInstructor());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView courseTitle;
        TextView courseInstructor;
        Button viewCourseButton;


        public ViewHolder(@NonNull View item) {
            super(item);

            courseTitle = item.findViewById(R.id.courseTitleDisplay);
            courseInstructor = item.findViewById(R.id.courseInstructorDisplay);
            viewCourseButton = item.findViewById(R.id.recycleCourseButton);


            viewCourseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        CourseLog courseLog = Dataset.get(position);

                        Intent intent = new Intent(context,ViewCourse.class);
                        intent.putExtra("courseTitle",courseLog.getTitle());
                        intent.putExtra("username",currUser.getUsername());
                        intent.putExtra("pass",currUser.getPassword());
                        view.getContext().startActivity(intent);

                    }
                }
            });

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION) {
                CourseLog courseLog = Dataset.get(position);

                Intent intent = new Intent(context,ViewCourse.class);
                intent.putExtra("courseTitle",courseLog.getTitle());
                intent.putExtra("username",currUser.getUsername());
                intent.putExtra("pass",currUser.getPassword());
                v.getContext().startActivity(intent);

            }

        }


    }

}

package com.example.cst438project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cst438project1.DB.CourseLog;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<CourseLog> Dataset;

    Context context;


    public CourseAdapter(List<CourseLog> myDataset) {
        Dataset = myDataset;
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

        public ViewHolder(@NonNull View item) {
            super(item);

            courseTitle = item.findViewById(R.id.courseTitleDisplay);
            courseInstructor = item.findViewById(R.id.courseInstructorDisplay);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION) {
                CourseLog courseLog = Dataset.get(position);

                // push course info through to next page

            }

        }


    }

}

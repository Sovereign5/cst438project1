package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cst438project1.model.AssignmentDatabase;
import com.example.cst438project1.model.Grade;

import java.util.List;

public class GradeActivity extends AppCompatActivity {

    List<Grade> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignment);
        AssignmentDatabase db = AssignmentDatabase.getAppDatabase(getBaseContext());
        db.loadData(this);
        db.getAssignmentDAO();
        grades = AssignmentDatabase.getAppDatabase(this).getGradeDAO().getAllGrades();
        Log.d("GradeActivity", "Grade size: " + grades.size());

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        rv.setAdapter( new Adapter() );

    }

    private class Adapter  extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(GradeActivity.this);
            return new  ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.bind(grades.get(position));
        }

        @Override
        public int getItemCount() { return grades.size(); }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Grade f ) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(f.toString());
        }
    }
}

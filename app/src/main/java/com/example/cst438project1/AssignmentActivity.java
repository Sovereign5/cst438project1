package com.example.cst438project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cst438project1.model.AppDatabase;
import com.example.cst438project1.model.Assignment;

import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    List<Assignment> assignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDatabase db = AppDatabase.getAppDatabase(getBaseContext());
        db.loadData(this);
        db.getAssignmentDAO();
        assignments = AppDatabase.getAppDatabase(this).getAssignmentDAO().getAllAssignments();
        Log.d("AssignmentActivity", "Assignment size: " + assignments.size());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assignment);

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager( new LinearLayoutManager(this));
        rv.setAdapter( new Adapter() );

    }

    private class Adapter  extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(AssignmentActivity.this);
            return new  ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.bind(assignments.get(position));
        }

        @Override
        public int getItemCount() { return assignments.size(); }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Assignment f ) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(f.toString());
        }
    }
}

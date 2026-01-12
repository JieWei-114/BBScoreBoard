package com.ls.bbscoreboard;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ls.bbscoreboard.CourseBadminton;
import com.ls.bbscoreboard.DBHandlerForBadminton;
import com.ls.bbscoreboard.badmintonAdapter;

import java.util.ArrayList;

public class viewBadmintonHistory extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseBadminton> CourseBadmintonArrayList;
    private DBHandlerForBadminton dbHandler;
    private com.ls.bbscoreboard.badmintonAdapter badmintonAdapter;
    private RecyclerView badmintonRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_badminton_history);

        // initializing our all variables.
        CourseBadmintonArrayList = new ArrayList<>();
        dbHandler = new DBHandlerForBadminton(viewBadmintonHistory.this);

        // getting our course array
        // list from db handler class.
        CourseBadmintonArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        badmintonAdapter = new badmintonAdapter(CourseBadmintonArrayList, viewBadmintonHistory.this);
        badmintonRV = findViewById(R.id.idRVResult);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewBadmintonHistory.this, RecyclerView.VERTICAL, false);
        badmintonRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        badmintonRV.setAdapter(badmintonAdapter);
    }
}
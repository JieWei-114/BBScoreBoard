package com.ls.bbscoreboard;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ls.bbscoreboard.CourseBasketball;
import com.ls.bbscoreboard.DBHandlerForBasketball;
import com.ls.bbscoreboard.basketballAdapter;

import java.util.ArrayList;

public class viewBasketballHistory extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseBasketball> CourseBasketballArrayList;
    private DBHandlerForBasketball DBHandler;
    private com.ls.bbscoreboard.basketballAdapter basketballAdapter;
    private RecyclerView basketballRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basketball_history);

        // initializing our all variables.
        CourseBasketballArrayList = new ArrayList<>();
        DBHandler = new DBHandlerForBasketball(viewBasketballHistory.this);

        // getting our course array
        // list from db handler class.
        CourseBasketballArrayList = DBHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        basketballAdapter = new basketballAdapter(CourseBasketballArrayList, viewBasketballHistory.this);
        basketballRV = findViewById(R.id.idRVResult);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewBasketballHistory.this, RecyclerView.VERTICAL, false);
        basketballRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        basketballRV.setAdapter(basketballAdapter);
    }
}
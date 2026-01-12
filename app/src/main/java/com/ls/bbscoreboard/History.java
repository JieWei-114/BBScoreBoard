package com.ls.bbscoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class History extends AppCompatActivity {

    Button badmintonhistory,basketballhistory,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_history);

        badmintonhistory = findViewById(R.id.butbadhis);
        basketballhistory = findViewById(R.id.butbashis);
        back = findViewById(R.id.butbck);

        badmintonhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(History.this, viewBadmintonHistory.class);
                startActivity(i);
            }
        });

        basketballhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(History.this, viewBasketballHistory.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(History.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
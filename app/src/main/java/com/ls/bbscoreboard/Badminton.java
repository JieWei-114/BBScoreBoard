package com.ls.bbscoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Period;

public class Badminton extends AppCompatActivity {

    Button plusA,plusB,plusC,plusD,plusE,minA,minB,minC,minD,minE,resetA;
    ImageButton resetButton, endButton;
    private DBHandlerForBadminton dbHandlerBadminton;

    int scoreA = 0;
    int scoreB = 0;
    int wintimeA = 0;
    int wintimeB = 0;
    int period = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_badminton);

        //Button
        plusA = findViewById(R.id.butpls7);
        plusB = findViewById(R.id.butpls8);
        plusC = findViewById(R.id.butpls9);
        plusD = findViewById(R.id.butpls10);
        plusE = findViewById(R.id.butpls11);
        minA = findViewById(R.id.butmn7);
        minB = findViewById(R.id.butmn8);
        minC = findViewById(R.id.butmn9);
        minD = findViewById(R.id.butmn10);
        minE = findViewById(R.id.butmn11);
        resetA = findViewById(R.id.butreset3);
        //Image button
        endButton = findViewById(R.id.butend3);
        resetButton = findViewById(R.id.butreset4);
        //SQLite Database
        dbHandlerBadminton = new DBHandlerForBadminton(Badminton.this);
        //Button listener
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teamASetNumber = Integer.toString(wintimeA);
                String teamBSetNumber = Integer.toString(wintimeB);
                if (wintimeA==0 && wintimeB==0) {
                    Toast.makeText(Badminton.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Badminton.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandlerBadminton.addResult(teamASetNumber, teamBSetNumber);
                    // after adding the data we are displaying a toast message.
                    Toast.makeText(Badminton.this, "Result Has Been Added.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Badminton.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    // count the marks for team A & team B
    public void ScoreAplus(View view) {
        scoreA = scoreA + 1;
        displayForA(scoreA);
    }

    public void ScoreAmin(View view) {
        if(scoreA==0){
        }else
            scoreA = scoreA - 1;
        displayForA(scoreA);
    }

    private void displayForA(int score) {
        TextView scoreViewA = (TextView) findViewById(R.id.score5);
        scoreViewA.setText("" + score);
    }
    public void ScoreBplus(View view) {
        scoreB = scoreB + 1;
        displayForB(scoreB);
    }

    public void ScoreBmin(View view) {
        if(scoreB==0){
        }else
            scoreB = scoreB - 1;
        displayForB(scoreB);
    }

    private void displayForB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score6);
        scoreView.setText(String.valueOf(score));
    }

    // reset the marks for both team
    public void resetBut(View view) {
        scoreA = 0;
        scoreB = 0;
        displayForA(0);
        displayForB(0);
    }

    // sets number for team A
    public void wintimeaddA(View view) {
        wintimeA = wintimeA + 1;
        displaywintimeA(wintimeA);
    }

    public void wintimeminA(View view) {
        if(wintimeA==0){

        }else
            wintimeA = wintimeA - 1;
        displaywintimeA(wintimeA);
    }

    private void displaywintimeA(int round) {
        TextView timewin = (TextView) findViewById(R.id.round);
        timewin.setText("" + round);
    }

    // sets number for team B
    public void wintimeaddB(View view) {
        wintimeB = wintimeB + 1;
        displaywintimeB(wintimeB);
    }

    public void wintimeminB(View view) {
        if(wintimeB==0){

        }else
            wintimeB = wintimeB - 1;
        displaywintimeB(wintimeB);
    }

    private void displaywintimeB(int round) {
        TextView wintimeB = (TextView) findViewById(R.id.round2);
        wintimeB.setText("" + round);
    }

    // number of quarter
    public void periodadd(View view) {
        if(period ==5){

        }else
            period = period + 1;
        displayperiod(period);
    }

    public void periodminus(View view) {
        if(period==0){

        }else
            period = period - 1;
        displayperiod(period);
    }

    private void displayperiod(int period) {
        TextView period2 = (TextView) findViewById(R.id.period2);
        period2.setText("" + period);
    }

    // reset all the values
    public void resetAll(View view) {
        scoreA = 0;
        scoreB = 0;
        displayForA(0);
        displayForB(0);
        wintimeA = 0;
        wintimeB = 0;
        displaywintimeA(0);
        displaywintimeB(0);
        period = 0;
        displayperiod(0);
    }
}
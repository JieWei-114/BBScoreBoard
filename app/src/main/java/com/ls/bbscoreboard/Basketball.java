package com.ls.bbscoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Basketball extends AppCompatActivity {

    Button start, reset, end, plus0, plus1, plus2, plus3, plus4, plus5, min0, min1, min2, min3, min4, min5 ;
    ImageButton starttime, resettime ;
    private TextView TextViewCountDown, TextViewshotclock;
    private DBHandlerForBasketball dbHandlerBasketball;

    //Count down timer declaration
    private static final long START_TIME_IN_MILLIS = 600000; //10minutes
    private static final long SHOT_CLOCK = 24000; //24seconds

    //Count down timer
    private CountDownTimer CountDownTimer, CountDownTimerForShotClock;
    private boolean TimerRunning;
    private boolean shotClockrunning;
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;
    private long TimeLeftInMillis2 = SHOT_CLOCK;

    //Score declaration
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    //Team A & B fouls & quarter
    int teamAfouls = 0;
    int teamBfouls = 0;
    int quarter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_basketball);

        //Button
        start = findViewById(R.id.butstart);
        reset = findViewById(R.id.butreset1);
        end = findViewById(R.id.butend);
        plus0 = findViewById(R.id.butpls1);
        plus1 = findViewById(R.id.butpls2);
        plus2 = findViewById(R.id.butpls3);
        plus3 = findViewById(R.id.butpls4);
        plus4 = findViewById(R.id.butpls5);
        plus5 = findViewById(R.id.butpls6);
        min0 = findViewById(R.id.butmn1);
        min1 = findViewById(R.id.butmn2);
        min2 = findViewById(R.id.butmn3);
        min3 = findViewById(R.id.butmn4);
        min4 = findViewById(R.id.butmn5);
        min5 = findViewById(R.id.butmn6);

        //Image Button
        starttime = findViewById(R.id.butstart1);
        resettime = findViewById(R.id.butreset2);

        TextViewCountDown = findViewById(R.id.timer);
        TextViewshotclock = findViewById(R.id.timer2);

        //SQLite Database
        dbHandlerBasketball = new DBHandlerForBasketball(Basketball.this);

        //listener for end
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teamAScore = Integer.toString(scoreTeamA);
                String teamBScore = Integer.toString(scoreTeamB);
                if (scoreTeamA==0 && scoreTeamB==0) {
                    Toast.makeText(Basketball.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Basketball.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandlerBasketball.addResult(teamAScore, teamBScore);
                    // after adding the data we are displaying a toast message.
                    Toast.makeText(Basketball.this, "Result Has Been Added.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Basketball.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        //shotclock
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotClockrunning) {
                    pauseShotClock();
                } else {
                    startShotClock();
                }
            }
        });

        resettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetshotclock();
            }
        });
        updateCountDownText2();

        // timer 10 minutes
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

        plus0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtime();
            }
        });
        updateCountDownText();

        min0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minustime();
            }
        });
        updateCountDownText();
    }

    private void startShotClock() {
        CountDownTimerForShotClock = new CountDownTimer(TimeLeftInMillis2, 1000) {
            @Override
            public void onTick(long millisUntilFinished2) {
                TimeLeftInMillis2 = millisUntilFinished2;
                updateCountDownText2();
                resettime.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                shotClockrunning = false;

            }
        }.start();
        shotClockrunning = true;
    }

    private void pauseShotClock() {
        CountDownTimerForShotClock.cancel();
        shotClockrunning = false;
    }

    private void resetshotclock() {
        if(SHOT_CLOCK==24000){
            CountDownTimerForShotClock.cancel();
        }else
            CountDownTimerForShotClock.cancel();
            TimeLeftInMillis2 = SHOT_CLOCK;
        updateCountDownText2();
    }

    private void updateCountDownText2() {
        int minutes = (int) (TimeLeftInMillis2 / 1000) / 60;
        int seconds = (int) (TimeLeftInMillis2 / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        TextViewshotclock.setText(timeLeftFormatted);
    }

    private void startTimer() {
        CountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                plus0.setVisibility(View.INVISIBLE);
                min0.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                start.setText("Start");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();

        TimerRunning = true;
        start.setText("pause");
        reset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        CountDownTimer.cancel();
        TimerRunning = false;
        start.setText("Start");
        reset.setVisibility(View.VISIBLE);
        plus0.setVisibility(View.INVISIBLE);
        min0.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        plus0.setVisibility(View.VISIBLE);
        min0.setVisibility(View.VISIBLE);
    }

    private void addtime() {
        TimeLeftInMillis = TimeLeftInMillis + 60000;
        updateCountDownText();
    }

    private void minustime() {
        if(TimeLeftInMillis==60000){
            Toast.makeText(getApplicationContext(),"One minute is the minimum time!", Toast.LENGTH_LONG).show();
        }else
            TimeLeftInMillis = TimeLeftInMillis - 60000;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (TimeLeftInMillis / 1000) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        TextViewCountDown.setText(timeLeftFormatted);
    }

    // count the marks for team A & team B
    public void teamAOneScore(View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    public void teamAMinusOneScore(View view) {
        if(scoreTeamA==0){

        }else
            scoreTeamA = scoreTeamA - 1;
        displayForTeamA(scoreTeamA);
    }

    private void displayForTeamA(int score) {
        TextView scoreViewA = (TextView) findViewById(R.id.score);
        scoreViewA.setText("" + score);
    }
    public void teamBOneScore(View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void teamBMinusOneScore(View view) {
        if(scoreTeamB==0){

        }else
            scoreTeamB = scoreTeamB - 1;
        displayForTeamB(scoreTeamB);
    }

    private void displayForTeamB(int score) {
        TextView scoreViewB = (TextView) findViewById(R.id.score2);
        scoreViewB.setText(String.valueOf(score));
    }

    // reset the marks for both team
    public void resetButton(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(0);
        displayForTeamB(0);
    }

    // fouls number for team A
    public void teamafouladd(View view) {
        teamAfouls = teamAfouls + 1;
        displayteamafouls(teamAfouls);
    }

    public void teamafoulminus(View view) {
        if(teamAfouls==0){

        }else
            teamAfouls = teamAfouls - 1;
        displayteamafouls(teamAfouls);
    }

    private void displayteamafouls(int foul) {
        TextView teamafoul = (TextView) findViewById(R.id.score3);
        teamafoul.setText("" + foul);
    }

    // fouls number for team B
    public void teambfouladd(View view) {
        teamBfouls = teamBfouls + 1;
        displayteambfouls(teamBfouls);
    }

    public void teambfoulminus(View view) {
        if(teamBfouls==0){

        }else
            teamBfouls = teamBfouls - 1;
        displayteambfouls(teamBfouls);
    }

    private void displayteambfouls(int foul) {
        TextView teambfoul = (TextView) findViewById(R.id.score4);
        teambfoul.setText("" + foul);
    }

    // number of quarter
    public void periodadd(View view) {
        if(quarter==4){

        }else
            quarter = quarter + 1;
        displayperiod(quarter);
    }

    public void periodminus(View view) {
        if(quarter==0){

        }else
            quarter = quarter - 1;
        displayperiod(quarter);
    }

    private void displayperiod(int quarter) {
        TextView period = (TextView) findViewById(R.id.period);
        period.setText("" + quarter);
    }
}
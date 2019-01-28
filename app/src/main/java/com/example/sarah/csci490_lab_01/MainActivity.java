package com.example.sarah.csci490_lab_01;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startB = this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = this.findViewById(R.id.timer);
        timeElapsedView = this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));

    }

    public void onClick(View view){
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText(R.string.stop);
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText(R.string.reset);
        }
    }

    public class MyCountDownTimer extends CountDownTimer {

        String time_remain = getResources().getString(R.string.time_remain);
        String time_elapsed = getResources().getString(R.string.time_elapsed);
        String time_up = getResources().getString(R.string.time_up);

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        public void onFinish(){
            text.setText(time_up);
            timeElapsedView.setText(time_elapsed + String.valueOf(startTime));
        }

        public void onTick(long millisUntilFinished){
            text.setText(time_remain + String.valueOf(millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(time_elapsed + String.valueOf(timeElapsed));

        }

    }
}

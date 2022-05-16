package com.example.szoftverfejleszts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Foprogram extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private Button start, stop, reset;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foprogram);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);
        dbHelper = new DatabaseHelper(this);
        if (savedInstanceState != null) {

            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    public void onClickStart(View v)
    {
        running = true;
    }
    public void onClickStop(View v)
    {
        running = false;
    }
    public void onClickReset(View v)
    {
        running = false;
        Bundle extras = getIntent().getExtras();
        String vn = extras.getString("extra_vnev");
        String kn = extras.getString("extra_knev");
        boolean igen = dbHelper.eredmenyHozzaad(vn, kn, seconds);
        if(igen){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            seconds = 0;
        }else{
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }

    }

    private void runTimer() {

        final TextView timeView
                = (TextView) findViewById(
                R.id.stopper);

        final Handler handler
                = new Handler();


        handler.post(new Runnable() {
            @Override

            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);


                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }
}
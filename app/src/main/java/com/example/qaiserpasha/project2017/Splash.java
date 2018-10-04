package com.example.qaiserpasha.project2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
 int a;
    LinearLayout up,down;
    Animation uptodown , downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().setTitle("Splash");
        up=(LinearLayout)findViewById(R.id.UP);
        down=(LinearLayout)findViewById(R.id.DOWN);
        uptodown= AnimationUtils.loadAnimation(this, R.anim.uptodown);
        up.setAnimation(uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        down.setAnimation(downtoup);
        doSomethingRepeatedly();
    }
    private void doSomethingRepeatedly() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                try {

                    a = a + 1;
                    if (a == 2) {
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        finish();
                    }


                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }, 0, 1000);
    }
}

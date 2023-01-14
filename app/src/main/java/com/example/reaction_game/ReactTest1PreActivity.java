package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class ReactTest1PreActivity extends AppCompatActivity {

    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1_pre);
        description = (TextView) findViewById(R.id.reactDesc);
    }

    @SuppressLint("WrongConstant")
    public void timerFunction(View v){
        findViewById(R.id.bgPink).setClickable(false);
        description.setVisibility(-1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (new Random().nextInt((3 - 1) + 1) + 1); i++) {
                    timer(1000);
                    goToMid();
                }
            }
        }, 1);
        description.setVisibility(-1);
    }

    public void goToMid(){
        Intent myIntent = new Intent(this, ReactTest1Mid.class);
        startActivity(myIntent);
    }

    public void timer(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Random;

public class ReactTest1PreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1_pre);
    }

    @SuppressLint("WrongConstant")
    public void timerFunction(View v){
        findViewById(R.id.bgPink).setClickable(false);
        findViewById(R.id.reactDesc).setVisibility(-1);
        try {
            Thread.sleep(new Random().nextInt((5000 - 2000) + 1) + 2000);
            Intent myIntent = new Intent(this, ReactTest1Mid.class);
            startActivity(myIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
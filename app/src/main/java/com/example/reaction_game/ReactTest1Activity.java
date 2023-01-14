package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class ReactTest1Activity extends AppCompatActivity {

    private ImageView finitoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1);
    }

    @SuppressLint("WrongConstant")
    public void goToReact(View v){
        finitoImage.setVisibility(0);
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    @SuppressLint("WrongConstant")
    public void timerFunction(View v){
        Random rand = new Random();
        int timer = rand.nextInt((5000 - 2000) + 1) + 2000;
        finitoImage = findViewById(R.id.bgBlue);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finitoImage.setVisibility(1);
    }
}
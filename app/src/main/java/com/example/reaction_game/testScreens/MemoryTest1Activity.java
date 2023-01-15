package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.reaction_game.R;

import java.util.Random;

public class MemoryTest1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test1);
    }

    @SuppressLint("WrongConstant")
    public void startMTest1(View v){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                for (int i = 0; i < (new Random().nextInt((3 - 1) + 1) + 1); i++) {
//                    timer(1000);
//                }
//                Intent myIntent = new Intent(v.getContext(), ReactTest1Activity.class);
//                startActivity(myIntent);
            }
        }, 1);
        findViewById(R.id.descMemoryT1).setVisibility(-1);
        findViewById(R.id.bgMT1desc).setVisibility(-1);
    }

    public void timer(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
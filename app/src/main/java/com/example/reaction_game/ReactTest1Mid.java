package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ReactTest1Mid extends AppCompatActivity {

    static long startTime = 0;
    static double resultTime = 0;
    static double resultBest = 0;
    static double resultAVG = 0;
    static ArrayList<Double> results = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1_mid);
        startTime = System.currentTimeMillis();
    }

    public void goToResult(View v){
        resultTime = (System.currentTimeMillis() - startTime);
        if (resultTime < resultBest || resultBest == 0) {
            resultBest = resultTime;
        }
        results.add(resultTime);
        resultAVG = calcAVG();
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }

    public double calcAVG(){
        double avg = 0;
        for (double result: results) {
            avg += result;
        }
        avg /= results.size();
        return avg;
    }
}
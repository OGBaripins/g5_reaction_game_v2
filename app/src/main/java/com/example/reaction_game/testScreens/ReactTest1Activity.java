package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.reaction_game.R;

import java.util.ArrayList;

public class ReactTest1Activity extends AppCompatActivity {

    static long startTime = 0;
    static double resultTime = 0;
    static double resultBest = 0;
    static double resultAVG = 0;
    static ArrayList<Double> results = new ArrayList<Double>();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1);
        startTime = System.currentTimeMillis();
        sp = getSharedPreferences("UserScores",Context.MODE_PRIVATE);
    }

    public void goToResult(View v){
        resultTime = (System.currentTimeMillis() - startTime);
        if (resultTime < resultBest || resultBest == 0) {
            resultBest = resultTime;
        }
        results.add(resultTime);
        resultAVG = calcAVG();
        saveToSP(resultBest, resultAVG);
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

    public void saveToSP(double resultBest, double resultAVG){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("gamesPlayed", sp.getInt("gamesPlayed",0) + 1);
        editor.putString("resultBest", resultBest + "");
        editor.putString("resultAVG", resultAVG + "");
        editor.commit();
    }
}
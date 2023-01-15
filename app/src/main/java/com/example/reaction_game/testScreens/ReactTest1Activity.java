package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.reaction_game.R;

public class ReactTest1Activity extends AppCompatActivity {

    static long startTime = 0;
    static double resultTime = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1);
        startTime = System.currentTimeMillis();
        sp = getSharedPreferences("UserScores",Context.MODE_PRIVATE);
    }

    public void goToResult(View v){
        resultTime = (System.currentTimeMillis() - startTime);
        editor = sp.edit();
        editor.putInt("gamesPlayedRT1", sp.getInt("gamesPlayedRT1",0) + 1);
        if (resultTime < sp.getFloat("resultRT1Best",0) || sp.getFloat("resultRT1Best",0) == 0) {
            editor.putFloat("resultRT1Best", (float)resultTime);
        }
        editor.putFloat("resultRT1Sum", sp.getFloat("resultRT1Sum",0) + (float)resultTime);
        editor.putFloat("resultRT1AVG", (sp.getFloat("resultRT1Sum",0) / sp.getInt("gamesPlayedRT1", 1)));
        editor.commit();
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }
}
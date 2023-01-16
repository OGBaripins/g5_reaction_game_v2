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
    SharedPreferences sp, sp_user;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1);
        startTime = System.currentTimeMillis();
        sp = getSharedPreferences("UserScores",Context.MODE_PRIVATE);
        sp_user = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
    }

    public void goToResult(View v){
        resultTime = (System.currentTimeMillis() - startTime);
        if(sp_user.getBoolean("isLoggedIn", false)){ // To not count scores if not logged in!!
            editor = sp.edit();
            editor.putInt("all_games_played", sp.getInt("all_games_played",0) + 1); // Take if you need to count all games played!!
            editor.putInt("CH_games_played", sp.getInt("CH_games_played",0) + 1);
            if (resultTime < sp.getFloat("CH_best_result",0) || sp.getFloat("CH_best_result",0) == 0) {
                editor.putFloat("CH_best_result", (float)resultTime);
            }
            editor.putFloat("CH_result_sum", sp.getFloat("CH_result_sum",0) + (float)resultTime);
            editor.putFloat("CH_result_average", (sp.getFloat("CH_result_sum",0) / sp.getInt("CH_games_played", 1)));
            editor.commit();
        }
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }
}
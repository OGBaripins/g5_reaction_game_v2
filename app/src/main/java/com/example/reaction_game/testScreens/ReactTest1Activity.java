package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.reaction_game.R;

public class ReactTest1Activity extends AppCompatActivity {

    ImageView blueBackground;
    ImageView pinkBackground;
    static long startTime = 0;
    static double resultTime = 0;
    static boolean clickedTooFast = true;
    SharedPreferences sp, sp_user;
    SharedPreferences.Editor editor;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1_pre);
        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        sp_user = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        blueBackground = findViewById(R.id.bgBlue);
        pinkBackground = findViewById(R.id.bgPink);
        pinkBackground.setClickable(false);
    }

    @SuppressLint("WrongConstant")
    public void startRTest1(View v){
        findViewById(R.id.descReactT1).animate().xBy(-3000).setDuration(200);
        pinkBackground.setClickable(true);
        blueBackground.animate().xBy(10).setDuration(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                blueBackground.animate().xBy(2200).setDuration(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        startTime = System.currentTimeMillis();
                    }
                });
            }
        });
    }

    public void goToFail(View v){
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }

    public void goToResult(View v){
        clickedTooFast = false;
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
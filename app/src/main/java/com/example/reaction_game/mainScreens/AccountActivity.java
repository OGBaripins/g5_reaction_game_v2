package com.example.reaction_game.mainScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;
    DecimalFormat df = new DecimalFormat("0.0000");
    TextView game_played_value, mct_best_result_value, ch_best_result_value;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        game_played_value = findViewById(R.id.game_played_value); mct_best_result_value = findViewById(R.id.mct_best_result_value); ch_best_result_value = findViewById(R.id.ch_best_result_value);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.account);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.account:
                    break;
                case R.id.settings:
                    goToSettings();
                    break;
                case R.id.home:
                    gotoMainMenu();
            }
            return false;
        });

        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);

        if(sp.getBoolean("isLoggedIn", false)) {
            game_played_value.setText(Integer.toString(sp.getInt("all_games_played", 0)));
            ch_best_result_value.setText(df.format(sp.getFloat("CH_best_result", 0) / 1000));
            mct_best_result_value.setText(Integer.toString(sp.getInt("MCT_best_result", 0))); // For memory test
        }

    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void gotoStats(View view){
        Intent myIntent = new Intent(this, StatsActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void goToCamera(View v){
        Intent myIntent = new Intent(this, PhotoActivity.class);
        startActivity(myIntent);
    }
}
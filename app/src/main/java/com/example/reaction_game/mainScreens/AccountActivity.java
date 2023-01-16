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
    TextView game_played_value, best_reaction_value, avg_reaction_value;

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        game_played_value = findViewById(R.id.game_played_value); best_reaction_value = findViewById(R.id.best_reaction_value); avg_reaction_value = findViewById(R.id.avg_reaction_value);

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

        game_played_value.setText(df.format(sp.getInt("gamesPlayedRT1", 0)));
        best_reaction_value.setText(df.format(sp.getFloat("resultRT1Best", 0)/1000));
        avg_reaction_value.setText(df.format(sp.getFloat("resultRT1AVG", 0)/1000));

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
}
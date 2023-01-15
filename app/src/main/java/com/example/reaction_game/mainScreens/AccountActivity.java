package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;
    String temp;
    TextView game_played_value, best_reaction_value, avg_reaction_value;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        game_played_value = findViewById(R.id.game_played_value); best_reaction_value = findViewById(R.id.best_reaction_value); avg_reaction_value = findViewById(R.id.avg_reaction_value);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.account);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });

        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);

        game_played_value.setText(""+sp.getInt("gamesPlayedRT1", 0));
        best_reaction_value.setText(""+sp.getFloat("resultRT1Best", 0));
        avg_reaction_value.setText(""+sp.getFloat("resultRT1AVG", 0));

    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }
}
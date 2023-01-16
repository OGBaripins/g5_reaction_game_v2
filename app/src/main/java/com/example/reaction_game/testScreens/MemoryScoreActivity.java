package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.example.reaction_game.mainScreens.SelectMemoryActivity;

public class MemoryScoreActivity extends AppCompatActivity {

    SharedPreferences sp, sp_user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_score);
        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        sp_user = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        TextView text = findViewById(R.id.textLevelReached);
        text.setText(MemoryTest1Activity.level + "");
        text = findViewById(R.id.textScore);
        text.setText(MemoryTest1Activity.score + "");
        text = findViewById(R.id.textScoreBest);
        if(sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
            text.setText(Integer.toString(sp.getInt("MCT_best_result", 0)));
        } else {
            text.setText(0+"");
        }
        text = findViewById(R.id.textScoreAVG);
        if(sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
            text.setText(Integer.toString(sp.getInt("MCT_result_average", 0)));
        } else {
            text.setText(0+"");
        }
    }

    public void goToMemorySelect(View v){
        Intent myIntent = new Intent(this, SelectMemoryActivity.class);
        startActivity(myIntent);
    }

    public void goMTest1(View v){
        Intent myIntent = new Intent(this, MemoryTest1Activity.class);
        startActivity(myIntent);
    }
}
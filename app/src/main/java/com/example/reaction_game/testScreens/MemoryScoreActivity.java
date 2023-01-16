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

    SharedPreferences sp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_score);
        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        TextView text = findViewById(R.id.textLevelReached);
        text.setText(MemoryTest1Activity.level + "");
        text = findViewById(R.id.textScore);
        text.setText(MemoryTest1Activity.score + "");
        text = findViewById(R.id.textScoreBest);
        text.setText(sp.getInt("resultMemoryT1Best", 0) + "");
        text = findViewById(R.id.textScoreAVG);
        text.setText(sp.getInt("resultMemoryT1AVG", 0) + "");
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
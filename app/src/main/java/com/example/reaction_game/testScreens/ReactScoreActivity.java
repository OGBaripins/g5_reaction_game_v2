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
import com.example.reaction_game.mainScreens.SelectReactActivity;

import java.text.DecimalFormat;

public class ReactScoreActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("0.0000");
    SharedPreferences sp, sp_user;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_score);
        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        sp_user = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        TextView text = findViewById(R.id.textResultNow);
        text.setText(df.format(ReactTest1Activity.resultTime / 1000) + " sec");
        text = findViewById(R.id.textResultBest);
        if(sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
            text.setText(df.format(sp.getFloat("CH_best_result",0)/ 1000) + " sec");
        } else {
            text.setText(0+"");
        }
        text = findViewById(R.id.textResultAVG);
        if(sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
            text.setText(df.format(sp.getFloat("CH_result_average",0)/ 1000) + " sec");
        } else {
            text.setText(0+"");
        }
    }

    public void goToReact(View v){
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    public void goRTest1(View v){
        Intent myIntent = new Intent(this, ReactTest1PreActivity.class);
        startActivity(myIntent);
    }
}
package com.example.reaction_game.mainScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.reaction_game.R;
import com.example.reaction_game.testScreens.ReactTest1PreActivity;

public class SelectReactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_react);
    }

    public void goBack(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goRTest1(View v){
        Intent myIntent = new Intent(this, ReactTest1PreActivity.class);
        startActivity(myIntent);
    }
}
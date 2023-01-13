package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}
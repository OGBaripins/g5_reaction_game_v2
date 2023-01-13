package com.example.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToReact(View v){
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    public void goToMemory(View v){
        Intent myIntent = new Intent(this, SelectMemoryActivity.class);
        startActivity(myIntent);
    }
}
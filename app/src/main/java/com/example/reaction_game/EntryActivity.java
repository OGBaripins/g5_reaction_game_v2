package com.example.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Button login_btn = (Button) findViewById(R.id.entry_login_btn);
        Button register_btn = (Button) findViewById(R.id.entry_register_btn);
        Button later_btn =  findViewById(R.id.entry_later_btn);
        login_btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, LoginActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
        });
        register_btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, RegisterActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
        });
        later_btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, EntryActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
        });
    }

    public void goToLogin(View v){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    public void goToRegister(View v){
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }

}
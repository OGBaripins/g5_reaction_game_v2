package com.example.reaction_game.startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.reaction_game.mainScreens.MainActivity;
import com.example.reaction_game.R;

public class EntryActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.commit();
    }

    public void goToLogin(View v){
        Intent myIntent = new Intent(this, LoginActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

    public void goToMainMenu(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

    public void goToRegister(View v){
        Intent myIntent = new Intent(this, RegisterActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

}
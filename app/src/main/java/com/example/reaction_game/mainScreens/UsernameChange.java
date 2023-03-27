package com.example.reaction_game.mainScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsernameChange extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;

    @Override
    public void onBackPressed() {}

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_change);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.account:
                    goToAccount();
                    break;
                case R.id.settings:
                    goToSettings();
                    break;
                case R.id.home:
                    gotoMainMenu();
                    break;
            }
            return false;
        });
    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToAccount(){
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void changeUsername(View view){

        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String cur_username = sp.getString("username", null);
        EditText username_str = findViewById(R.id.new_username_field);

        if(cur_username != null && !cur_username.equals(username_str.getText().toString())){
            if(username_str.getText().toString().length() >= 3){
                editor.putString("username", username_str.getText().toString());
                editor.apply();
                goToSettings();
            }else{
                Toast.makeText(UsernameChange.this, "Username has to be more than 3 characters long", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(UsernameChange.this, "Cant changed username to the same one.", Toast.LENGTH_SHORT).show();
        }

    }
}
package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.example.reaction_game.startScreens.EntryActivity;
import com.example.reaction_game.startScreens.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;
    SharedPreferences sp_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);
        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        sp_score = getSharedPreferences("UserScores", Context.MODE_PRIVATE);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        if(sp.getBoolean("isLoggedIn", false)){
                            goToAccount();
                        }else{
                            Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.settings:
                        break;
                    case R.id.home:
                        gotoMainMenu();
                        break;
                }
                return false;
            }
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

    public void gotoEntry(){
        Intent myIntent = new Intent(this, EntryActivity.class);
        startActivity(myIntent);
    }

    public void gotoEntryOnClick(View view){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLoggedIn",false);
        editor.commit();
        Intent myIntent = new Intent(this, EntryActivity.class);
        startActivity(myIntent);
    }

    public void gotoUsernameChange(View view){
        if(!sp.getBoolean("isLoggedIn", false)){
            Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent myIntent = new Intent(this, UsernameChange.class);
        startActivity(myIntent);
    }

    public void resetScores(View view){
        if(!sp.getBoolean("isLoggedIn", false)){
            Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
            return;
        }
        Dialog dialog = new Dialog(SettingsActivity.this);

        dialog.setContentView(R.layout.popup_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView okay_text = dialog.findViewById(R.id.okay_text);
        TextView cancel_text = dialog.findViewById(R.id.cancel_text);

        okay_text.setOnClickListener(v -> {
            SharedPreferences.Editor score_editor = sp_score.edit();
            score_editor.putInt("gamesPlayedRT1", 0);
            score_editor.putFloat("resultRT1Best", 0);
            score_editor.putFloat("resultRT1AVG", 0);
            score_editor.commit();
            Toast.makeText(SettingsActivity.this,"Scores has been reset", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        });

        cancel_text.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.reaction_game.R;
import com.example.reaction_game.startScreens.EntryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        goToAccount();
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

    public void gotoUsernameChange(){
        Intent myIntent = new Intent(this, UsernameChange.class);
        startActivity(myIntent);
    }

    public void resetScores(){
        Intent myIntent = new Intent(this, UsernameChange.class);
        startActivity(myIntent);
    }
}
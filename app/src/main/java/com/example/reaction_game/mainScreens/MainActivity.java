package com.example.reaction_game.mainScreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reaction_game.R;
import com.example.reaction_game.testScreens.AudioActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.home);
        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        if(sp.getBoolean("isLoggedIn", false)){
                            goToAccount();
                        }else{
                            Toast.makeText(MainActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
                        }

                        // ====================== FIX CHECK FOR USER BEING LOGGED IN
                        goToAccount();
                        break;
                    case R.id.settings:
                        goToSettings();
                        break;
                    case R.id.home:
                        break;
                }
                return false;
            }
        });
    }

    public void goToReact(View v){
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    public void goToMemory(View v){
        Intent myIntent = new Intent(this, SelectMemoryActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void goToAccount(){
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }
}
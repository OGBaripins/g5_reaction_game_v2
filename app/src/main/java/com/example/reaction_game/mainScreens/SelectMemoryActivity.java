package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.example.reaction_game.testScreens.MemoryTest1Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SelectMemoryActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_memory);

        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        if(sp.getBoolean("isLoggedIn", false)){
                            goToAccount();
                        }else{
                            Toast.makeText(SelectMemoryActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.settings:
                        goToSettings();
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

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void goMTest1(View v){
        Intent myIntent = new Intent(this, MemoryTest1Activity.class);
        startActivity(myIntent);
    }
}
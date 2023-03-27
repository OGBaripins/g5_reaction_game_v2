package com.example.reaction_game.mainScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.example.reaction_game.testScreens.MemoryTest1Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SelectMemoryActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    public void onBackPressed() {}

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_memory);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.home);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.account:
                    if(FirebaseAuth.getInstance().getCurrentUser() != null){
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
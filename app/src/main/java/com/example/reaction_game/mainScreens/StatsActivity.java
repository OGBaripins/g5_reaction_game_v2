package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;
import java.util.Map;

public class StatsActivity extends AppCompatActivity {

    SharedPreferences sp;
    BottomNavigationView nav;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.account);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });

        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout);

        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        Map<String, ?> map = sp.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            TextView tv = new TextView(this);
            if(entry.getKey().split("_")[entry.getKey().split("_").length-1].equals("sum")){continue;}
            tv.setText(""+entry.getKey().replace("_"," ").toUpperCase(Locale.ROOT) + " : " + entry.getValue());
            tv.setTextSize(20);
            tv.setPadding(10, 10, 0, 10);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            ll.addView(tv);
        }

    }

    public void goToAccount(){
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

}
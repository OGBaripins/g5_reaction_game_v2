package com.example.reaction_game.mainScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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

    public void resetScores(View view){
        Toast.makeText(SettingsActivity.this,"Sore has been reset", Toast.LENGTH_LONG).show();

        Dialog dialog = new Dialog(SettingsActivity.this);

        dialog.setContentView(R.layout.popup_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView okay_text = dialog.findViewById(R.id.okay_text);
        TextView cancel_text = dialog.findViewById(R.id.cancel_text);

        okay_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(SettingsActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
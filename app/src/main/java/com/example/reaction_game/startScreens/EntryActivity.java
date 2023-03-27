package com.example.reaction_game.startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.reaction_game.mainScreens.MainActivity;
import com.example.reaction_game.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EntryActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Toast.makeText(EntryActivity.this,"Already Logged in!", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
        }else{
            Toast.makeText(EntryActivity.this,"Not logged in!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
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
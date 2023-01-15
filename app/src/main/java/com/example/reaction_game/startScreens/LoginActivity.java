package com.example.reaction_game.startScreens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reaction_game.mainScreens.MainActivity;
import com.example.reaction_game.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    SharedPreferences sp;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.login_username_field);
        password = findViewById(R.id.login_password_field);

        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("isLoggedIn", false);
        editor.commit();
    }

    public void checkLogin(View v){
        SharedPreferences.Editor editor = sp.edit();

        if(sp.getString("username",null).equals(username.getText().toString()) && sp.getString("password",null).equals(password.getText().toString())){
            editor.putBoolean("isLoggedIn",true);
            editor.commit();
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        } else {
            Toast.makeText(this,"Incorrect credentials", Toast.LENGTH_LONG).show();
        }
    }

    public void goToEntry(View v){
        Intent myIntent = new Intent(this, EntryActivity.class);
        startActivity(myIntent);
    }
}
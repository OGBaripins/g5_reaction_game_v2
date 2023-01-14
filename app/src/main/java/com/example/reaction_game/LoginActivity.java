package com.example.reaction_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login_btn;
    SharedPreferences sp;
    String username_str, password_str;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.login_username_field);
        password = findViewById(R.id.login_password_field);
    }

    public void checkLogin(View v){
        //if(sp.getString("username",null).equals(username.getText().toString())){
        //if(sp.getString("password",null).equals(password.getText().toString())){
        if(getString(R.string.username).equals(username.getText().toString()) && getString(R.string.password).equals(password.getText().toString())){
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
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
        login_btn = findViewById(R.id.login_submit_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this,"Click worked i guess", Toast.LENGTH_LONG).show();
                username_str = username.getText().toString();
                password_str = password.getText().toString();

                if(sp.getString("username",null).equals(username_str)){

                    if(sp.getString("password",null).equals(password_str)){
                        Toast.makeText(LoginActivity.this,"Login Successfully", Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(myIntent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Incorrect Credentials2", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this,"Incorrect Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToEntry(View v){
        Intent myIntent = new Intent(this, EntryActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }
}
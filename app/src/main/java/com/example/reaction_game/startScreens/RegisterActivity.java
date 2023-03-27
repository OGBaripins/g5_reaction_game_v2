package com.example.reaction_game.startScreens;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reaction_game.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    //ToDo: Add checks for logged in users (with firebase now)
    //ToDo: Maybe add google sign in???

    private FirebaseAuth mAuth;
    EditText email, password;
    Button register_btn;
    String email_str, password_str;


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null){
//            Toast.makeText(RegisterActivity.this,"Logged in!", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(RegisterActivity.this,"Not logged in!", Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email_field); password = findViewById(R.id.register_password_field);
        register_btn = findViewById(R.id.button_login_submit);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_str = email.getText().toString();
                password_str = password.getText().toString();
                if(!email_str.isEmpty() && !password_str.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email_str, password_str)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_LONG).show();
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(myIntent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(RegisterActivity.this, "Please fill out both fields.", Toast.LENGTH_SHORT).show();
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
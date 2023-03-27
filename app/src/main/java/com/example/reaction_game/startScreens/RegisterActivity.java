package com.example.reaction_game.startScreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reaction_game.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //ToDo: Add checks for logged in users (with firebase now)
    //ToDo: Maybe add google sign in???

    private FirebaseAuth mAuth;
    EditText email, password;
    Button register_btn;
    String email_str, password_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email_field); password = findViewById(R.id.register_password_field);
        register_btn = findViewById(R.id.button_login_submit);

        register_btn.setOnClickListener(view -> {
            email_str = email.getText().toString();
            password_str = password.getText().toString();
            if(!email_str.isEmpty() && !password_str.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email_str, password_str)
                        .addOnCompleteListener(RegisterActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_LONG).show();

                                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(myIntent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(RegisterActivity.this, "Please fill out both fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToEntry(View v){
        Intent myIntent = new Intent(this, EntryActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }
}
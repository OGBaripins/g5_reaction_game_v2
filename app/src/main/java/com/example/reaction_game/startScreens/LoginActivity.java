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

import com.example.reaction_game.mainScreens.MainActivity;
import com.example.reaction_game.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    String email_str, password_str;
    Button login_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_email_field); password = findViewById(R.id.login_password_field);
        login_btn = findViewById(R.id.button_login_submit);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_str = email.getText().toString(); password_str = password.getText().toString();
                if(!email_str.isEmpty() && !password_str.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email_str, password_str)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "Authentication successful.",
                                                Toast.LENGTH_SHORT).show();

                                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(myIntent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "Please fill out both fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToEntry(View v){
        Intent myIntent = new Intent(this, EntryActivity.class);
        startActivity(myIntent);
    }
}
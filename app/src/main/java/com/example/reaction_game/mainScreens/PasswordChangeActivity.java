package com.example.reaction_game.mainScreens;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class PasswordChangeActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;

    @Override
    public void onBackPressed() {}

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);

        nav.setOnItemSelectedListener(item -> {
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

    public void changeUsername(View view){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        EditText old_pwd = findViewById(R.id.new_username_field);
        EditText new_pwd = findViewById(R.id.new_username_field2);

        if(old_pwd.getText().toString().length() >= 8 && new_pwd.getText().toString().length() >= 8){
            assert user != null;
            AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(user.getEmail()), old_pwd.getText().toString());
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(new_pwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Password updated");
                                            Toast.makeText(PasswordChangeActivity.this, "Password updated", Toast.LENGTH_SHORT).show();
                                            goToSettings();
                                        } else {
                                            Toast.makeText(PasswordChangeActivity.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, "Error password not updated");
                                        }
                                    }
                                });
                            } else {
                                Log.d(TAG, "Error auth failed");
                            }
                        }
                    });
        }else{
            Toast.makeText(PasswordChangeActivity.this, "Password has to be more than 8 characters long", Toast.LENGTH_SHORT).show();
        }
    }
}
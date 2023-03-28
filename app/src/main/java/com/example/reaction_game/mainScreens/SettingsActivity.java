package com.example.reaction_game.mainScreens;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.example.reaction_game.startScreens.EntryActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView nav;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onBackPressed() {}

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.settings);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.account:
                    if(FirebaseAuth.getInstance().getCurrentUser() != null){
                        goToAccount();
                    }else{
                        Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.settings:
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

    public void gotoEntryOnClick(View view){
        FirebaseAuth.getInstance().signOut();
        Intent myIntent = new Intent(this, EntryActivity.class);
        startActivity(myIntent);
    }

    public void gotoUsernameChange(View view){
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent myIntent = new Intent(this, UsernameChange.class);
        startActivity(myIntent);
    }

    public void resetScores(View view){
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Toast.makeText(SettingsActivity.this,"Please log in to access this.", Toast.LENGTH_LONG).show();
            return;
        }
        Dialog dialog = new Dialog(SettingsActivity.this);

        dialog.setContentView(R.layout.popup_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView okay_text = dialog.findViewById(R.id.okay_text);
        TextView cancel_text = dialog.findViewById(R.id.cancel_text);

        okay_text.setOnClickListener(v -> {

            String cur_user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            assert cur_user_email != null;

            db.collection(cur_user_email)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getId().equals("AVATAR")){continue;}
                                db.collection(cur_user_email).document(document.getId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error deleting document", e);
                                            }
                                        });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }
                    });






            dialog.dismiss();
        });

        cancel_text.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
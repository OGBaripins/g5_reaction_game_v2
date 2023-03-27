package com.example.reaction_game.testScreens;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.example.reaction_game.mainScreens.SelectMemoryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MemoryScoreActivity extends AppCompatActivity {

    SharedPreferences sp, sp_user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> return_data;

    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_score);
        String cur_user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        assert cur_user_email != null;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        db.collection(cur_user_email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(!document.getId().equals("MCT")){continue;}
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                return_data = document.getData();
                                TextView text = findViewById(R.id.textLevelReached);
                                text.setText(MemoryTest1Activity.level + "");
                                text = findViewById(R.id.textScore);
                                text.setText(MemoryTest1Activity.score + "");
                                text = findViewById(R.id.textScoreBest);

                                if(FirebaseAuth.getInstance().getCurrentUser() != null) { // To not count scores if not logged in!!
                                    text.setText(Objects.requireNonNull(return_data.get("MCT_best_result")).toString());
                                } else {
                                    text.setText(0+"");
                                }
                                text = findViewById(R.id.textScoreAVG);
                                if(FirebaseAuth.getInstance().getCurrentUser() != null) { // To not count scores if not logged in!!
                                    text.setText(Objects.requireNonNull(return_data.get("MCT_result_average")).toString());
                                } else {
                                    text.setText(0+"");
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void goToMemorySelect(View v){
        Intent myIntent = new Intent(this, SelectMemoryActivity.class);
        startActivity(myIntent);
    }

    public void goMTest1(View v){
        Intent myIntent = new Intent(this, MemoryTest1Activity.class);
        startActivity(myIntent);
    }

    public void goToAudioRecord(View v){
        Intent myIntent = new Intent(this, AudioActivity.class);
        startActivity(myIntent);
    }

    public void goToVideoRecord(View v){
        Intent myIntent = new Intent(this, VideoActivity.class);
        startActivity(myIntent);
    }
}
package com.example.reaction_game.testScreens;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.reaction_game.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class ReactTest1Activity extends AppCompatActivity {

    ImageView blueBackground;
    ImageView pinkBackground;
    static long startTime = 0;
    static double resultTime = 0;
    static boolean clickedTooFast;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> return_data;
    Map<String, Object> return_data1;

    @Override
    public void onBackPressed() {}

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_test1_pre);
        clickedTooFast = true;
        blueBackground = findViewById(R.id.bgBlue);
        pinkBackground = findViewById(R.id.bgPink);
        pinkBackground.setClickable(false);
    }

    @SuppressLint("WrongConstant")
    public void startRTest1(View v){
        findViewById(R.id.descReactT1).animate().xBy(-3000).setDuration(200);
        pinkBackground.setClickable(true);
        blueBackground.animate().xBy(10).setDuration(new Random().nextInt((2000)) + 1000).withEndAction(()
                -> blueBackground.animate().xBy(2200).setDuration(0).withEndAction(()
                -> startTime = System.currentTimeMillis()));
    }

    public void goToFail(View v){
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }

    public void goToResult(View v){
        clickedTooFast = false;
        resultTime = (System.currentTimeMillis() - startTime);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){ // To not count scores if not logged in!!
            String cur_user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            assert cur_user_email != null;
            general_games_played(cur_user_email);
            db.collection(cur_user_email)
                    .get()
                    .addOnCompleteListener(task -> {
                        Map<String, Object> user = new HashMap<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(!document.getId().equals("CH")){continue;}
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                return_data = document.getData();
                                Log.d(TAG, return_data + " return data "+ return_data.isEmpty());

                                Log.d(TAG, "yes very " + Integer.parseInt(Objects.requireNonNull(return_data.get("CH_games_played")).toString()));
                                user.put("CH_games_played", Integer.parseInt(Objects.requireNonNull(return_data.get("CH_games_played")).toString()) + 1);
                                if (resultTime < Float.parseFloat(Objects.requireNonNull(return_data.get("CH_best_result")).toString()) || Float.parseFloat(Objects.requireNonNull(return_data.get("CH_best_result")).toString()) == 0) {
                                    user.put("CH_best_result", (float)resultTime);
                                }else{
                                    user.put("CH_best_result", Float.parseFloat(Objects.requireNonNull(return_data.get("CH_best_result")).toString()));
                                }
                                user.put("CH_result_sum", Float.parseFloat(Objects.requireNonNull(return_data.get("CH_result_sum")).toString())+(float)resultTime);
                                user.put("CH_result_average", Float.parseFloat(Objects.requireNonNull(return_data.get("CH_result_sum")).toString())/ Integer.parseInt(Objects.requireNonNull(return_data.get("CH_games_played")).toString()));
                            }
//                                Log.d(TAG, return_data + " return data (reactionTest1) "+ return_data.isEmpty());
                            if(return_data == null){
                                Log.d(TAG, "Empty!!! ");
                                user.put("CH_games_played", 1);
                                user.put("CH_best_result", (float)resultTime);
                                user.put("CH_result_sum", (float)resultTime);
                                user.put("CH_result_average", (float)resultTime);
                            }
                            // Add a new document with a generated ID
                            db.collection(cur_user_email).document("CH")
                                    .set(user)
                                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                    .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                            Log.d(TAG, "Damn thats crazy"+ task.getResult().size());
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }
                    });
        }
        Intent myIntent = new Intent(this, ReactScoreActivity.class);
        startActivity(myIntent);
    }

    public void general_games_played(String cur_user_email){
        db.collection(cur_user_email)
                .get()
                .addOnCompleteListener(task -> {
                    Map<String, Object> userr = new HashMap<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(!document.getId().equals("GENERAL")){continue;}
                            return_data1 = document.getData();
                            userr.put("all_games_played", Integer.parseInt(Objects.requireNonNull(return_data1.get("all_games_played")).toString()) + 1);
                        }
                        if(return_data1 == null){
                            userr.put("all_games_played", 1);
                        }
                        db.collection(cur_user_email).document("GENERAL")
                                .set(userr)
                                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                    } else {
                        Log.d(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
}
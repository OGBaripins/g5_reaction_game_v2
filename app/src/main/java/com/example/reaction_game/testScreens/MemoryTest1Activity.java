package com.example.reaction_game.testScreens;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class MemoryTest1Activity extends AppCompatActivity {

    ImageView sqIndicator;
    static int level, score;
    int index, patternDelay = 300, sqCount = 4, move, timerIndex;
    int[] coordinates = new int[sqCount * 2 + 2];
    ArrayList<Integer> pattern = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> return_data;

    @Override
    public void onBackPressed() {}

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test1);
        sqIndicator = findViewById(R.id.memorySqIndicator);
        addCoordinates(-8000, -8000, 0); // offScreen
        addCoordinates(16, 42, 1); // sq1
        addCoordinates(537, 42, 2); // sq2
        addCoordinates(16, 987, 3); // sq3
        addCoordinates(537, 987, 4); // sq4
        index = 0;
        level = 0;
        move = 0;
        score = 0;
        timerIndex = 0;
    }

    @SuppressLint("WrongConstant")
    public void startMTest1(View v) {
        findViewById(R.id.bgMT1desc).animate().xBy(-3000).setDuration(200);
        findViewById(R.id.descMemoryT1).animate().yBy(-3000).setDuration(200);
        startLevel(v);
    }

    public void startLevel(View v) {
        Toast.makeText(this, "Starting level " + level, Toast.LENGTH_SHORT).show();
        pattern.clear();
        index = 0;
        move = 0;
        timerIndex = 0;
        for (int i = 0; i < (level + 1); i++) {
            pattern.add((new Random().nextInt((4 - 1) + 1) + 1));
        }
        pattern.add(0);
        Animation moveIndicator = new Animation() {};
        moveIndicator.setDuration(patternDelay);
        moveIndicator.setRepeatCount(pattern.size() * 2);
        moveIndicator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sqIndicator.animate().setDuration(300);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationRepeat(Animation animation) {
                if (timerIndex % 2 != 0) {
                    sqIndicator.animate().setDuration(0).alpha(1).x(coordinates[pattern.get(index)]).y(coordinates[pattern.get(index) + sqCount]);
                    index++;
                } else {
                    sqIndicator.animate().setDuration(patternDelay).alpha(0).start();
                }
                timerIndex++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        v.startAnimation(moveIndicator);
    }

    public void addCoordinates(int x, int y, int sqi) {
        coordinates[sqi] = x;
        coordinates[sqi + sqCount] = y;
    }

    public void clickSquare1(View v) {
        clickOperation(1, v);
    }

    public void clickSquare2(View v) {
        clickOperation(2, v);
    }

    public void clickSquare3(View v) {
        clickOperation(3, v);
    }

    public void clickSquare4(View v) {
        clickOperation(4, v);
    }

    public void clickOperation(int i, View v) {
        if (pattern.get(move) == i) {
            move++;
            score += move;
            if (move == (pattern.size() - 1)) {
                level++;
                startLevel(v);
            }
        } else {
            endTest(v);
        }
    }
    public void endTest(View v) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) { // To not count scores if not logged in!!

            // Create a new user with a first, middle, and last name
            String cur_user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            assert cur_user_email != null;
            general_games_played(cur_user_email);
            db.collection(cur_user_email)
                    .get()
                    .addOnCompleteListener(task -> {
                        Map<String, Object> user = new HashMap<>();
                        if (task.isSuccessful()) {

                            if(task.getResult().size() == 0){
                                Log.d(TAG, "Damn thats crazy AAAAAAAAAAAAAAAAAA"+ task.getResult().size());
                                user.put("all_games_played", 1);
                                user.put("MCT_games_played", 1);
                                user.put("MCT_best_result", score);
                                user.put("MCT_result_sum", score);
                                user.put("MCT_result_average", score);
                            }

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(!document.getId().equals("MCT")){continue;}
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                return_data = document.getData();
                                Log.d(TAG, return_data + " return data");
                                user.put("MCT_games_played", Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_games_played")).toString()) + 1);
                                if (score > Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_best_result")).toString()) ||
                                        Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_best_result")).toString()) == 0) {
                                    user.put("MCT_best_result", score);
                                }else{
                                    user.put("MCT_best_result", Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_best_result")).toString()));
                                }
                                user.put("MCT_result_sum", Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_result_sum")).toString())+score);
                                user.put("MCT_result_average", Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_result_sum")).toString())/ Integer.parseInt(Objects.requireNonNull(return_data.get("MCT_games_played")).toString()));
                            }
                            // Add a new document with a generated ID
                            db.collection(cur_user_email).document("MCT")
                                    .set(user)
                                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                    .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                            Log.d(TAG, "Damn thats crazy"+ task.getResult().size());
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }
                    });
        }
        Intent myIntent = new Intent(this, MemoryScoreActivity.class);
        startActivity(myIntent);
    }

    public void general_games_played(String cur_user_email){
        db.collection(cur_user_email)
                .get()
                .addOnCompleteListener(task -> {
                    Map<String, Object> user = new HashMap<>();
                    if (task.isSuccessful()) {
                        if(task.getResult().size() == 0){
                            user.put("all_games_played", 1);
                        }
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(!document.getId().equals("GENERAL")){continue;}
                            return_data = document.getData();
                            user.put("all_games_played", Integer.parseInt(Objects.requireNonNull(return_data.get("all_games_played")).toString()) + 1);
                            }
                        db.collection(cur_user_email).document("GENERAL")
                                .set(user)
                                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                    } else {
                        Log.d(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
}
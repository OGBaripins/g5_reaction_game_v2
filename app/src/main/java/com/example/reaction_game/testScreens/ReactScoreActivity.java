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
import com.example.reaction_game.mainScreens.SelectReactActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

public class ReactScoreActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("0.0000");
    SharedPreferences sp, sp_user;
    TextView text;

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
        setContentView(R.layout.activity_react_score);

        if (ReactTest1Activity.clickedTooFast){
            TextView text = findViewById(R.id.textFail);
            text.setText("You clicked the screen too fast!\nWant to try again?");
        } else {


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
                                    if(!document.getId().equals("CH")){continue;}
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    return_data = document.getData();

                                    text = findViewById(R.id.textScoreStats);
                                    text.setVisibility(View.VISIBLE);
                                    text = findViewById(R.id.textResultNow);
                                    text.setText(df.format(ReactTest1Activity.resultTime / 1000) + " sec");
                                    text = findViewById(R.id.textResultBest);
                                    if(FirebaseAuth.getInstance().getCurrentUser() != null) { // To not count scores if not logged in!!
                                        text.setText(df.format(Float.parseFloat(Objects.requireNonNull(return_data.get("CH_best_result")).toString()) / 1000) + " sec");
                                    } else {
                                        text.setText(0+"");
                                    }
                                    text = findViewById(R.id.textResultAVG);
                                    if (FirebaseAuth.getInstance().getCurrentUser() != null) { // To not count scores if not logged in!!
                                        text.setText(df.format(Float.parseFloat(Objects.requireNonNull(return_data.get("CH_result_average")).toString()) / 1000) + " sec");
                                    } else {
                                        text.setText(0 + "");
                                    }
                                }
                            } else {
                                Log.d(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });

//            text = findViewById(R.id.textScoreStats);
//            text.setVisibility(View.VISIBLE);
//            text = findViewById(R.id.textResultNow);
//            text.setText(df.format(ReactTest1Activity.resultTime / 1000) + " sec");
//            text = findViewById(R.id.textResultBest);
//            if (sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
//                text.setText(df.format(sp.getFloat("CH_best_result", 0) / 1000) + " sec");
//            } else {
//                text.setText(0 + "");
//            }
//            text = findViewById(R.id.textResultAVG);
//            if (sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
//                text.setText(df.format(sp.getFloat("CH_result_average", 0) / 1000) + " sec");
//            } else {
//                text.setText(0 + "");
//            }
        }
    }

    public void goToReact(View v){
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    public void goRTest1(View v){
        Intent myIntent = new Intent(this, ReactTest1Activity.class);
        startActivity(myIntent);
    }
}
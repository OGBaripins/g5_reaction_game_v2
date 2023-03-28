package com.example.reaction_game.mainScreens;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class StatsActivity extends AppCompatActivity {

    SharedPreferences sp;
    BottomNavigationView nav;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> return_data;


    @Override
    public void onBackPressed() {
        // Do nothing lol
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.account);

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

        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout);

        String cur_user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        assert cur_user_email != null;

        db.collection(cur_user_email)
                .get()
                .addOnCompleteListener(task -> {
                    Map<String, Object> user = new HashMap<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(document.getId().equals("AVATAR")){continue;}
                            return_data = document.getData();
                            for(String key : return_data.keySet()){
                                TextView tv = new TextView(this);
                                tv.setText(""+key.replace("_"," ").toUpperCase(Locale.ROOT) + " : " + return_data.get(key));
                                tv.setTextSize(20);
                                tv.setPadding(10, 10, 0, 10);
                                tv.setTextColor(Color.parseColor("#FFFFFF"));
                                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                ll.addView(tv);
                            }
                        }
                    } else {
                        Log.d(TAG, "Error getting documents.", task.getException());
                    }
                });



//        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
//        Map<String, ?> map = sp.getAll();
//
//        for (Map.Entry<String, ?> entry : map.entrySet()) {
//
//            if(entry.getKey().split("_")[entry.getKey().split("_").length-1].equals("sum")){continue;}
//
//        }

    }

    public void goToAccount(){
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

}
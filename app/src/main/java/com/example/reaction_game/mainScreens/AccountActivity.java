package com.example.reaction_game.mainScreens;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sp;
    DecimalFormat df = new DecimalFormat("0.0000");
    TextView game_played_value, mct_best_result_value, ch_best_result_value;
    ImageView avatarPicture;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> return_data;

    @Override
    public void onBackPressed() {}

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        avatarPicture = findViewById(R.id.imageAvatar);
        game_played_value = findViewById(R.id.game_played_value); mct_best_result_value = findViewById(R.id.mct_best_result_value); ch_best_result_value = findViewById(R.id.ch_best_result_value);

        setupAvatar();

        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.account);
        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.account:
                    break;
                case R.id.settings:
                    goToSettings();
                    break;
                case R.id.home:
                    gotoMainMenu();
            }
            return false;
        });

        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);

        if(sp.getBoolean("isLoggedIn", false)) {
            game_played_value.setText(Integer.toString(sp.getInt("all_games_played", 0)));
            ch_best_result_value.setText(df.format(sp.getFloat("CH_best_result", 0) / 1000));
            mct_best_result_value.setText(Integer.toString(sp.getInt("MCT_best_result", 0))); // For memory test
        }
    }

    public void setupAvatar(){
        String cur_user_email = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        assert cur_user_email != null;
        db.collection(cur_user_email)
                .get()
                .addOnCompleteListener(task -> {
                    Map<String, Object> user = new HashMap<>();
                    if (task.isSuccessful()) {
                        if(task.getResult().size() == 0){
                            user.put("avatarFilePath", "");
                        }
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(!document.getId().equals("AVATAR")){continue;}
                            return_data = document.getData();
                            user.put("avatarFilePath", Objects.requireNonNull(return_data.get("avatarFilePath")).toString());
                            //=======================================================================================================
                            File imgFile = new  File(Objects.requireNonNull(return_data.get("avatarFilePath")) +".jpg");
                            //File imgFile = new  File("Pictures/CameraX-Image/reactOnAvatarPicture.jpg");
                            if(imgFile.exists()){
                                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                                avatarPicture.setImageBitmap(myBitmap);
                            } else {
                                Toast.makeText(this,"File not found" + (return_data.get("avatarFilePath")), Toast.LENGTH_SHORT).show();
                            }
                            //=======================================================================================================
                        }
                        db.collection(cur_user_email).document("AVATAR")
                                .set(user)
                                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                    } else {
                        Log.d(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    public void gotoMainMenu(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void gotoStats(View view){
        Intent myIntent = new Intent(this, StatsActivity.class);
        startActivity(myIntent);
    }

    public void goToSettings(){
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    public void goToCamera(View v){
        Intent myIntent = new Intent(this, PhotoActivity.class);
        startActivity(myIntent);
    }
}
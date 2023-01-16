package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reaction_game.R;

import java.util.ArrayList;
import java.util.Random;


public class MemoryTest1Activity extends AppCompatActivity {

    ImageView sqIndicator;
    static int level, score;
    int index, patternDelay = 300, sqCount = 4, move, timerIndex;
    int[] coordinates = new int[sqCount*2+2];
    ArrayList<Integer> pattern = new ArrayList<>();
    SharedPreferences sp, sp_user;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test1);
        sp = getSharedPreferences("UserScores", Context.MODE_PRIVATE);
        sp_user = getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        sqIndicator = findViewById(R.id.memorySqIndicator);
        addCoordinates(-8000, -8000, 0); // offScreen
        addCoordinates(16, 42, 1); // sq1
        addCoordinates(537, 42, 2); // sq2
        addCoordinates(16, 987, 3); // sq3
        addCoordinates(537,987, 4); // sq4
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

    public void startLevel(View v){
        Toast.makeText(this,"Starting level " + level, Toast.LENGTH_SHORT).show();
        pattern.clear();
        index = 0;
        move = 0;
        timerIndex = 0;
        for (int i = 0; i < (level+1); i++) {
            pattern.add((new Random().nextInt((4 - 1) + 1) + 1));
        }
        pattern.add(0);
        Animation moveIndicator = new Animation() {
        };
        moveIndicator.setDuration(patternDelay);
        moveIndicator.setRepeatCount(pattern.size()*2);
        moveIndicator.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){
                sqIndicator.animate().setDuration(300);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationRepeat(Animation animation){
                if (timerIndex%2!=0){
                    sqIndicator.animate().setDuration(0).alpha(1).x(coordinates[pattern.get(index)]).y(coordinates[pattern.get(index)+sqCount]);
                    index++;
                } else{
                    sqIndicator.animate().setDuration(patternDelay).alpha(0).start();
                }
                timerIndex++;
            }

            @Override
            public void onAnimationEnd(Animation animation){
            }
        });
        v.startAnimation(moveIndicator);
    }

    public void addCoordinates(int x, int y, int sqi){
        coordinates[sqi] = x;
        coordinates[sqi + sqCount] = y;
    }

    public void clickSquare1(View v){
        clickOperation(1, v);
    }

    public void clickSquare2(View v){
        clickOperation(2, v);
    }

    public void clickSquare3(View v){
        clickOperation(3, v);
    }

    public void clickSquare4(View v){
        clickOperation(4, v);
    }

    public void clickOperation(int i, View v){
        if (pattern.get(move) == i) {
            move++;
            score += move;
            if (move == (pattern.size()-1)){
                level++;
                startLevel(v);
            }
        } else {
            endTest(v);
        }
    }

    public void endTest(View v){
        if(sp_user.getBoolean("isLoggedIn", false)) { // To not count scores if not logged in!!
            editor = sp.edit();
            editor.putInt("all_games_played", sp.getInt("all_games_played", 0) + 1); // Take if you need to count all games played!!
            editor.putInt("MCT_games_played", sp.getInt("MCT_games_played", 0) + 1);
            if (score > sp.getInt("MCT_best_result", 0) || sp.getInt("MCT_best_result", 0) == 0) {
                editor.putInt("MCT_best_result", score);
            }
            editor.putInt("MCT_result_sum", sp.getInt("MCT_result_sum", 0) + score);
            editor.putInt("MCT_result_average", (sp.getInt("MCT_result_sum", 0) / sp.getInt("MCT_games_played", 1)));
            editor.commit();
        }
        Intent myIntent = new Intent(this, MemoryScoreActivity.class);
        startActivity(myIntent);
    }
}
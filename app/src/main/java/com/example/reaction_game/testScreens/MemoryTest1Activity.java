package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reaction_game.R;
import com.example.reaction_game.mainScreens.SelectReactActivity;

import java.util.ArrayList;
import java.util.Random;


public class MemoryTest1Activity extends AppCompatActivity {

    ImageView sqIndicator;
    static int level, score;
    int index, patternDelay = 500, sqCount = 4, move;
    int[] coordinates = new int[sqCount*2+2];
    ArrayList<Integer> pattern = new ArrayList<>();
    SharedPreferences sp;
    SharedPreferences.Editor editor;

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
        addCoordinates(537,987, 4); // sq4
        index = 0;
        level = 0;
        move = 0;
        score = 0;
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
        for (int i = 0; i < (level+1); i++) {
            pattern.add((new Random().nextInt((4 - 1) + 1) + 1));
        }
        pattern.add(0);
        Animation moveIndicator = new RotateAnimation(0, 360) {
        };
        moveIndicator.setDuration(patternDelay);
        moveIndicator.setRepeatCount(pattern.size());
        moveIndicator.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){
                sqIndicator.animate().setDuration(0);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationRepeat(Animation animation){
                sqIndicator.animate().x(coordinates[pattern.get(index)]).y(coordinates[pattern.get(index)+sqCount]).rotation(360f);
                index++;
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
        Intent myIntent = new Intent(this, MemoryScoreActivity.class);
        startActivity(myIntent);
    }
}
package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reaction_game.R;

import java.util.ArrayList;


public class MemoryTest1Activity extends AppCompatActivity {

    ImageView sqIndicator;
    int index = 0, patternDelay = 1000, sqCount = 4;
    int[] coordinates = new int[sqCount*2];
    ArrayList<Integer> moves = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test1);
        sqIndicator = findViewById(R.id.memorySqIndicator);
        addCoordinates(16, 42, 0);
        addCoordinates(537, 42, 1);
        addCoordinates(16, 987, 2);
        addCoordinates(537,987, 3);
    }

    @SuppressLint("WrongConstant")
    public void startMTest1(View v) {
        findViewById(R.id.bgMT1desc).animate().xBy(-3000).setDuration(200);
        findViewById(R.id.descMemoryT1).animate().yBy(-3000).setDuration(200);
        moves.add(0);
        moves.add(1);
        moves.add(2);
        moves.add(3);
        Animation moveIndicator = new Animation() {
        };
        moveIndicator.setDuration(patternDelay);
        moveIndicator.setRepeatCount(moves.size());
        moveIndicator.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){
                sqIndicator.animate().setDuration(0);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationRepeat(Animation animation){
                sqIndicator.animate().x(coordinates[moves.get(index)]).y(coordinates[moves.get(index)+sqCount]);
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
}
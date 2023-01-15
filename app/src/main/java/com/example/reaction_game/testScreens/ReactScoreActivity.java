package com.example.reaction_game.testScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reaction_game.R;
import com.example.reaction_game.mainScreens.SelectReactActivity;

import java.text.DecimalFormat;

public class ReactScoreActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("0.0000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_score);
        TextView resNow = findViewById(R.id.textResultNow);
        resNow.setText(df.format(ReactTest1Activity.resultTime / 1000) + " sec");
        TextView resBest = findViewById(R.id.textResultBest);
        resBest.setText(df.format(ReactTest1Activity.resultBest / 1000) + " sec");
        TextView resAVG = findViewById(R.id.textResultAVG);
        resAVG.setText(df.format(ReactTest1Activity.resultAVG / 1000) + " sec");
    }

    public void goToReact(View v){
        Intent myIntent = new Intent(this, SelectReactActivity.class);
        startActivity(myIntent);
    }

    public void goRTest1(View v){
        Intent myIntent = new Intent(this, ReactTest1PreActivity.class);
        startActivity(myIntent);
    }
}
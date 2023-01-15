package com.example.reaction_game.startScreens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reaction_game.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    EditText email, username, password, password_rep;
    Button register_btn;
    SharedPreferences sp;
    String email_str, username_str, password_str, password_rep_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.register_email_field); username = findViewById(R.id.register_username_field);
        password = findViewById(R.id.register_password_field); password_rep = findViewById(R.id.register_password_field2);
        register_btn = findViewById(R.id.button_login_submit);

        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_str = email.getText().toString();
                username_str = username.getText().toString();
                password_str = password.getText().toString();
                password_rep_str = password_rep.getText().toString();

                SharedPreferences.Editor editor = sp.edit();

                if(validateEmail(email_str)){
                    editor.putString("email", email_str);
                }else{
                    Toast.makeText(RegisterActivity.this,"Email entered incorrectly", Toast.LENGTH_LONG).show();
                    return;
                }

                if(username_str.length() >= 3){
                    editor.putString("username", username_str);
                }else{
                    Toast.makeText(RegisterActivity.this,"Username has to be at least 3 characters long", Toast.LENGTH_LONG).show();
                    return;
                }

                if(password_str.length() >= 8){
                    editor.putString("password", password_str);
                }else{
                    Toast.makeText(RegisterActivity.this,"Password has to be at least 8 characters long", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!password_rep_str.equals(password_str)){
                    Toast.makeText(RegisterActivity.this,"Password's have to match", Toast.LENGTH_LONG).show();
                    return;
                }

                editor.commit();
                Toast.makeText(RegisterActivity.this,"Registered Successfully", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);

            }
        });
    }

    public void goToEntry(View v){
        Intent myIntent = new Intent(this, EntryActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches() && email.length() <= 254;
    }
}
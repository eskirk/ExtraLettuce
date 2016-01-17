package com.example.elliot.extralettuce;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elliot.extralettuce.support.Typefaces;

public class IntroActivity extends AppCompatActivity {

    TextView screenTitle;
    Button login, getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        screenTitle = (TextView) findViewById(R.id.introScreenTitle);
        screenTitle.setTypeface(Typefaces.yeahPapa(this));
        login = (Button) findViewById(R.id.login);
        getStarted = (Button) findViewById(R.id.get_started);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to Login
            }
        });
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
                IntroActivity.this.startActivity(intent);
            }
        });

    }

}

package com.example.elliot.extralettuce;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.elliot.extralettuce.support.Typefaces;

public class IntroActivity extends AppCompatActivity {

    TextView screenTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        screenTitle = (TextView) findViewById(R.id.introScreenTitle);
        screenTitle.setTypeface(Typefaces.yeahPapa(this));

    }

}

package com.example.elliot.extralettuce;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elliot.extralettuce.support.Typefaces;

import org.w3c.dom.Text;

public class LinkActivity extends AppCompatActivity {


    ImageView iconImage;
    TextView routingTextView;
    TextView accountTextView;
    TextView titleTextView;
    TextView captionTextView;
    Button continueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        iconImage = (ImageView) findViewById(R.id.linkIconImageView);
        routingTextView = (TextView) findViewById((R.id.linkRoutingTextView));
        accountTextView = (TextView) findViewById((R.id.linkAccountTextView));
        titleTextView = (TextView) findViewById(R.id.linkScreenTitle);
        captionTextView = (TextView) findViewById(R.id.linkScreenCaption);
        continueButton = (Button) findViewById(R.id.linkTransitionButton);
        titleTextView.setTypeface(Typefaces.yeahPapa(this));
    }

    public void attemptLink(View view) {

        System.out.println("YO dis is the shit");
    }


}

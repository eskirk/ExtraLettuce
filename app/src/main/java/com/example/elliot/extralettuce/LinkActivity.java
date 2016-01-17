package com.example.elliot.extralettuce;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LinkActivity extends AppCompatActivity {


    ImageView iconImage = (ImageView) findViewById(R.id.linkIconImageView);
    TextView routingTextView = (TextView) findViewById((R.id.linkRoutingTextView));
    TextView accountTextView = (TextView) findViewById((R.id.linkAccountTextView));
    TextView titleTextView = (TextView) findViewById(R.id.linkScreenTitle);
    TextView captionTextView = (TextView) findViewById(R.id.linkScreenCaption);
    Button continueButton = (Button) findViewById(R.id.linkTransitionButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void attemptLink(View view) {

        System.out.println("YO dis is the shit");
    }


}

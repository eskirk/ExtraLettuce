package com.example.elliot.extralettuce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elliot.extralettuce.support.Typefaces;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LinkActivity extends AppCompatActivity {


    protected ImageView iconImage;
    protected TextView routingTextView;
    protected TextView accountTextView;
    protected TextView titleTextView;
    protected TextView captionTextView;
    protected Button continueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        iconImage = (ImageView) findViewById(R.id.linkIconImageView);
        routingTextView = (TextView) findViewById((R.id.linkRoutingTextView));
        accountTextView = (TextView) findViewById((R.id.linkBankPasswordTextView));
        titleTextView = (TextView) findViewById(R.id.linkScreenTitle);
        captionTextView = (TextView) findViewById(R.id.linkScreenCaption);
        continueButton = (Button) findViewById(R.id.linkTransitionButton);
        titleTextView.setTypeface(Typefaces.yeahPapa(this));
    }

    public void attemptLink(View view) {

        System.out.println("YO dis is the shit");

        String URI = "";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, URI,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response ", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

        };

        queue.add(jsObjRequest);
    }

}

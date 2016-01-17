package com.example.elliot.extralettuce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
    protected EditText bankUsernameTextView;
    protected EditText bankPasswordTextView;
    protected TextView titleTextView;
    protected TextView captionTextView;
    protected Button continueButton;
    protected Spinner bankSelectDropDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        iconImage = (ImageView) findViewById(R.id.linkIconImageView);
        bankUsernameTextView = (EditText) findViewById(R.id.linkBankUsernameTextView);
        bankPasswordTextView = (EditText) findViewById(R.id.linkBankPasswordTextView);
        bankSelectDropDown = (Spinner) findViewById(R.id.linkBankSelectSpinner);

        titleTextView = (TextView) findViewById(R.id.linkScreenTitle);
        captionTextView = (TextView) findViewById(R.id.linkScreenCaption);
        continueButton = (Button) findViewById(R.id.linkTransitionButton);
        titleTextView.setTypeface(Typefaces.yeahPapa(this));
        String[] banks = new String[]{"Bank of America"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, banks);
    }

    public void attemptLink(View view) {

        System.out.println("YO dis is the shit");

        String URI = "http://www.extralettuce.co/account/link/";
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
                params.put("token", "6c5d9d793ebc4bf74875e2f04b8f89c3451e4bdc");
                params.put("bank_username",bankUsernameTextView.getText().toString() );
                params.put("bank_password",bankPasswordTextView.getText().toString());
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

package com.example.elliot.extralettuce;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.elliot.extralettuce.support.CustomJsonObjectRequest;
import com.example.elliot.extralettuce.support.Typefaces;

import org.json.JSONObject;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;


public class LinkActivity extends AppCompatActivity {


    protected ImageView iconImage;
    protected EditText bankUsernameTextView;
    protected EditText bankPasswordTextView;
    protected TextView titleTextView;
    protected TextView captionTextView;
    protected Button nextButton;
    protected Spinner bankSelectDropDown;
    protected String[] banks;
    private String selectedBank;
    private Map<String, String> bankInstitutionMap;
    private boolean infoValidated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        iconImage = (ImageView) findViewById(R.id.linkIconImageView);
        bankUsernameTextView = (EditText) findViewById(R.id.linkBankUsernameTextView);
        bankPasswordTextView = (EditText) findViewById(R.id.linkBankPasswordTextView);
        titleTextView = (TextView) findViewById(R.id.linkScreenTitle);
        captionTextView = (TextView) findViewById(R.id.linkScreenCaption);
        nextButton = (Button) findViewById(R.id.linkNextButton);
        titleTextView.setTypeface(Typefaces.yeahPapa(this));
        bankSelectDropDown = (Spinner) findViewById(R.id.linkBankSelectSpinner);

        infoValidated = false;
        banks = new String[]{"Bank of America", "Wells Fargo", "Chase"};
        bankInstitutionMap = new HashMap<>();
        bankInstitutionMap.put("Bank of America", "bofa");
        bankInstitutionMap.put("Wells Fargo", "wells");
        bankInstitutionMap.put("Chase", "chase");
        initSpinner();

    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, banks);
        bankSelectDropDown.setAdapter(adapter);
        bankSelectDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBank = banks[position];
                Log.d("selectedBank", selectedBank);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

        public void nextOnClick(View view) {
        if(infoValidated) {
            Intent returnHome = new Intent(LinkActivity.this, MainActivity.class);
            startActivity(returnHome);
        } else {
            attemptLink();
        }
    }

    public void attemptLink() {

          /*  @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };*/
        final String bankUsername = bankUsernameTextView.getText().toString();
        final String bankPassword = bankPasswordTextView.getText().toString();
        Log.d("bankUser", bankUsername);
        Log.d("bankPass", bankPassword);

        new AsyncTask<Void, Void, Void>(){

            class JsonObjectErrorListener implements Response.ErrorListener {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("success", error.getMessage());
                }
            }

            class JsonObjectResponseListener implements Response.Listener<JSONObject> {
                @Override
                public void onResponse(JSONObject response) {
                    //when if returns ok then parse info from server

                    try {
                        if(!response.has("errors")) {

                            Log.d("success", response.getString("token"));
                        } else {

                            Log.d("success", "status code: " + response.getString("errors"));
                        }
                    } catch(Exception e) {
                        Log.d("success", e.getMessage());
                    }

                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                Log.d("Succes", "In Background...");

                Map<String, String> payload = new HashMap<>();
                payload.put("bank_username", bankUsername);
                payload.put("bank_password", bankPassword);
                payload.put("institution", "wells");
                JsonObjectRequest request = new CustomJsonObjectRequest(LinkActivity.this, Request.Method.POST, Endpoints.BASE_URL+"/link/", new JSONObject(payload),
                        new JsonObjectResponseListener(), new JsonObjectErrorListener());
                Volley.newRequestQueue(LinkActivity.this).add(request);
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}

package com.example.elliot.extralettuce;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elliot.extralettuce.support.Typefaces;

import org.json.JSONObject;

public class RegisterActivity extends Activity {
    protected EditText userName;
    protected EditText userPassword;
    protected Button registerButton;
    protected TextView registerTitle;
    protected TextView registerInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.usernameRegisterEditText);
        userPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerTitle = (TextView) findViewById(R.id.registrationTitleTextView);
        registerInfo = (TextView) findViewById(R.id.registrationInfoTextView);

        registerTitle.setTypeface(Typefaces.yeahPapa(this));
        registerInfo.setTypeface(Typefaces.yeahPapa(this));
        //Listen to register button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = userName.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                //TO DO: implement Verify Passwords


                new AsyncTask<Void, Void, Void>(){

                    class JsonObjectErrorListener implements Response.ErrorListener {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //wehn error
                        }
                    }

                    class JsonObjectResponseListenor implements Response.Listener<JSONObject> {
                        @Override
                        public void onResponse(JSONObject response) {
                            //when if returns ok then parse info from server

                            try {
                                if((Integer) response.get("status") == 201) {
                                    System.out.println("success");
                                } else {
                                    System.out.println("status code: " + response.get("status"));
                                }
                            } catch(Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        //json object request
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.BASE_URL+"/create", new JSONObject(),
                                new JsonObjectResponseListenor(), new JsonObjectErrorListener());
                        Volley.newRequestQueue(RegisterActivity.this).add(request);
                        return null;
                    }
                };
                Intent returnHome = new Intent(RegisterActivity.this, LinkActivity.class);
                startActivity(returnHome);
                //www.extraleetuce.co/account/create
               //Client client = ClientBuilder.newClient();
               // WebTarget target = client.target("http://localhost:9998").path("resource");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
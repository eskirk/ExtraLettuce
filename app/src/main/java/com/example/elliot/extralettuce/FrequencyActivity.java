package com.example.elliot.extralettuce;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.elliot.extralettuce.R;
import com.example.elliot.extralettuce.fragments.GraphFragment;
import com.example.elliot.extralettuce.support.Typefaces;

public class FrequencyActivity extends AppCompatActivity {
    TextView titleTV, saveTV, perMonth;
    EditText editText;
    Button button;
    int saveAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency);

        titleTV = (TextView) findViewById(R.id.linkScreenTitle);
        titleTV.setTypeface(Typefaces.yeahPapa(this));
        saveTV = (TextView) findViewById(R.id.linkScreenCaption);
        editText = (EditText) findViewById(R.id.amount_et);
        button = (Button) findViewById(R.id.next_save);
        perMonth = (TextView) findViewById(R.id.textView3);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                next();
            }
        });

    }

    public void next() {
        perMonth.setVisibility(View.VISIBLE);
        if (editText.getText().toString() != null)
            saveAmount = Integer.parseInt(editText.getText().toString());

        button.setText("Let-tuce Save!");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lettuce();
            }
        });
    }

    public void lettuce() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                return null;
            }

            @Override
            protected void onPostExecute(Void res) {
                Intent intent = new Intent(FrequencyActivity.this, GraphFragment.class);
                FrequencyActivity.this.startActivity(intent);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //TODO start new Activity
    }
}

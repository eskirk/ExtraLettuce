package com.example.elliot.extralettuce;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elliot.extralettuce.adapters.GoalCardAdapter;
import com.example.elliot.extralettuce.dataClasses.Goal;
import com.example.elliot.extralettuce.fragments.BankFragment;
import com.example.elliot.extralettuce.fragments.GraphFragment;
import com.example.elliot.extralettuce.fragments.SettingsFragment;
import com.jjoe64.graphview.GraphView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String GRAPH_FRAGMENT = "GRAPH_FRAGMENT";
    private static final String BANK_FRAGMENT = "BANK_FRAGMENT";
    private static final String SETTINGS_FRAGMENT = "SETTINGS_FRAGMENT";
    private static final int GRAPH_POS = 0;
    private static final int BANK_POS = 1;
    private static final int SETTINGS_POS = 2;
    private int mPosition = GRAPH_POS;

    private GraphFragment graphFragment;
    private BankFragment bankFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        graphFragment = GraphFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(GRAPH_FRAGMENT)
                .replace(R.id.content_container, graphFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_activity_v2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (mPosition != GRAPH_POS) {
                graphFragment = GraphFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(GRAPH_FRAGMENT)
                        .replace(R.id.content_container, graphFragment)
                        .commit();
                mPosition = GRAPH_POS;
            }
        } else if (id == R.id.nav_bank) {
            if (mPosition != BANK_POS) {
                bankFragment = BankFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(BANK_FRAGMENT)
                        .replace(R.id.content_container, bankFragment)
                        .commit();
                mPosition = BANK_POS;
            }
        } else if (id == R.id.nav_settings) {
            if (mPosition != SETTINGS_POS) {
                settingsFragment = SettingsFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(SETTINGS_FRAGMENT)
                        .replace(R.id.content_container, settingsFragment)
                        .commit();
                mPosition = SETTINGS_POS;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    GraphView graph;
    int balance = 500;
    int goal = 800;

    public String[] displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText moneyVal = (EditText) alertLayout.findViewById(R.id.enter_Value);
        final EditText timePeriod = (EditText) alertLayout.findViewById(R.id.enter_Time);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Create a new Saving Goal");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Lettuce-leaf", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                String amount = moneyVal.getText().toString();
                String time = timePeriod.getText().toString();
                Toast.makeText(getBaseContext(),"Now saving $" + amount + " over " + time, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        String[] amt_time = new String[2];
        amt_time[0] = moneyVal.getText().toString();
        amt_time[1] = timePeriod.getText().toString();
        dialog.show();
        return amt_time;

    }



    public void button1Click(View view){
        Toast.makeText(getBaseContext(), "$" + balance + " out of your $" + goal + "!", Toast.LENGTH_SHORT).show();
    }

    public void returnBalance(View view){
        Toast.makeText(getBaseContext(), "$420.69", Toast.LENGTH_SHORT).show();
    }

    public void newGoalCard(View view){
        graphFragment.addGoal(new Goal("A rare pepe", 420, 365, 0));
    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
//
//        /**
//         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//         fab.setOnClickListener(new View.OnClickListener() {
//        @Override public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//        .setAction("Action", null).show();
//        }
//        });
//         */
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
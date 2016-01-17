package com.example.elliot.extralettuce.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elliot.extralettuce.Endpoints;
import com.example.elliot.extralettuce.Preferences;
import com.example.elliot.extralettuce.R;
import com.example.elliot.extralettuce.adapters.GoalCardAdapter;
import com.example.elliot.extralettuce.dataClasses.Goal;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphFragment extends Fragment {

    private RelativeLayout layout;
    private GraphView graph;
    private RecyclerView goalRecyclerView;
    private GoalCardAdapter goalCardAdapter;
    private SlideInLeftAnimationAdapter adapterWrapper;
    private List<Goal> goalList;
    private int balance;

    public GraphFragment() {
        // Required empty public constructor
    }

    public static GraphFragment newInstance() {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        goalCardAdapter = new GoalCardAdapter(new ArrayList<Goal>());
        adapterWrapper = new SlideInLeftAnimationAdapter(goalCardAdapter);
        adapterWrapper.setFirstOnly(false);
        goalCardAdapter.setAdapterWrapper(adapterWrapper);
        balance = getBalanceFromServer();
        getGoalsFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_graph, container, false);
        goalRecyclerView = (RecyclerView) layout.findViewById(R.id.goal_recycler);
        goalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        goalRecyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(1f)));
        goalRecyclerView.setAdapter(adapterWrapper);


        setupGraph();

        return layout;
    }

    public void addGoal(Goal newGoal) {
        if (goalCardAdapter != null) {
            goalCardAdapter.addGoal(newGoal);
        }

    }

    //Graphs will be setup using user deposit info
    public void setupGraph(){
        getDepositsFromServer();
        graph = (GraphView) layout.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 5),
                new DataPoint(3, 7),
                new DataPoint(4, 8)
        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setHighlightZeroLines(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph.getGridLabelRenderer().setVerticalAxisTitle("$");
        graph.setTitle("Progress");

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getContext(), "Clicked on data point " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getDepositsFromServer(){
        new AsyncTask<Void, Void, Void>() {

            class JSONObjectErrorListener implements Response.ErrorListener {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            class JSONObjectResponseListener implements Response.Listener<JSONObject> {
                @Override
                public void onResponse(JSONObject repsonse){

                }
            }
            @Override
        protected Void doInBackground(Void... params) {
                try {
                    SharedPreferences preferences = getContext().getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE);

                    JSONObject headerObject = new JSONObject();
                    headerObject.put("Authorization", "Token " + preferences.getString("TOKEN", ""));

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            Endpoints.BASE_URL + Endpoints.GOALS,
                            headerObject,
                            new JSONObjectResponseListener(),
                            new JSONObjectErrorListener());
                    Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

                } catch(Exception e) {

                }

                //TODO get dates/deposit values and add them to the graph as data points
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private void getGoalsFromServer() {
        new AsyncTask<Void, Void, Void>() {

            class JSONObjectErrorListener implements Response.ErrorListener {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

            class JSONObjectResponseListener implements Response.Listener<JSONObject> {

                 @Override
                 public void onResponse(JSONObject response) {

                 }
             }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SharedPreferences preferences = getContext().getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE);

                    JSONObject headerObject = new JSONObject();
                    headerObject.put("Authorization", "Token " + preferences.getString("TOKEN", ""));

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            Endpoints.BASE_URL + Endpoints.GOALS,
                            headerObject,
                            new JSONObjectResponseListener(),
                            new JSONObjectErrorListener());
                    Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

                } catch(Exception e) {

                }

                //TODO get goals and add each goal to goalCardAdapter.goalsList
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private int getBalanceFromServer() {
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "http://www.extralettuce.co/account/history";
        new AsyncTask<Void, Void, Void>() {

            class JSONObjectErrorListener implements Response.ErrorListener {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

            class JSONObjectResponseListener implements Response.Listener<JSONObject> {

                @Override
                public void onResponse(JSONObject response) {

                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SharedPreferences preferences = getContext().getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE);

                    JSONObject headerObject = new JSONObject();
                    headerObject.put("Authorization", "Token " + preferences.getString("TOKEN", ""));

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            Endpoints.BASE_URL + Endpoints.BALANCE,
                            headerObject,
                            new JSONObjectResponseListener(),
                            new JSONObjectErrorListener());
                    Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

                } catch(Exception e) {

                }

                //TODO get balance and add it to the toolbar
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return 0;
    }

    private void getDepositHistoryFromServer(){

        new AsyncTask<Void, Void, Void>() {

            class JSONObjectErrorListener implements Response.ErrorListener {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

            class JSONObjectResponseListener implements Response.Listener<JSONObject> {

                @Override
                public void onResponse(JSONObject response) {

                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    SharedPreferences preferences = getContext().getSharedPreferences(Preferences.PREF_NAME, Context.MODE_PRIVATE);

                    JSONObject headerObject = new JSONObject();
                    headerObject.put("Authorization", "Token " + preferences.getString("TOKEN", ""));

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            Endpoints.BASE_URL + Endpoints.HISTORY,
                            headerObject,
                            new JSONObjectResponseListener(),
                            new JSONObjectErrorListener());
                    Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

                } catch(Exception e) {

                }

                //TODO get goals and add each goal to goalCardAdapter.goalsList
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}

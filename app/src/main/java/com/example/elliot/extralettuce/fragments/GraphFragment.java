package com.example.elliot.extralettuce.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

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

    public GraphFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphFragment newInstance() {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_home_view, container, false);
        goalRecyclerView = (RecyclerView) layout.findViewById(R.id.goal_recycler);
        goalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        goalCardAdapter = new GoalCardAdapter(new ArrayList<Goal>());
        goalRecyclerView.setAdapter(goalCardAdapter);

        setupGraph();

        return layout;
    }

    public void setGoalList(List<Goal> goalList) {
        if (goalCardAdapter != null)
            goalCardAdapter.setGoalList(goalList);
    }

    public void addGoal(Goal newGoal) {
        if (goalCardAdapter != null)
            goalCardAdapter.addGoal(newGoal);
    }

    //Graphs will be setup using user deposit info
    public void setupGraph(){
        graph = (GraphView) layout.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 5),
                new DataPoint(3, 7),
                new DataPoint(4, 8)
        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getContext(), "Clicked on data point " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

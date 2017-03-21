package com.example.justinlam.coputingcup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class calendar extends AppCompatActivity {

    DBhelper dBhelper;
    List<databaseModelList> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String selectedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dBhelper = new DBhelper(this);
        dbList= new ArrayList<databaseModelList>();
        dbList = dBhelper.getDataFromDB();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_in_list);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new activityAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);

    }
}

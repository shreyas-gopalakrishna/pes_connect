package com.example.user.addapes;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlaceAdapter adapter;
    List<Placement> placements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView=(RecyclerView)findViewById(R.id.cardList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        initializeData();

        adapter = new PlaceAdapter(placements);
        recyclerView.setAdapter(adapter);
    }
    private void initializeData() {

        placements = new ArrayList<>();
        placements.add(new Placement("Microsoft","22th May","08:00am","Only CS and IS",R.drawable.microsoft));
        placements.add(new Placement("Intuit","12th May","09:00am","Only CS and IS",R.drawable.intuit));
        placements.add(new Placement("Sap Labs","02th Jun","08:00am","Only CS and IS",R.drawable.sap));
        placements.add(new Placement("Oracle","08th Oct","10:00am","All Branches",R.drawable.oracle));
        placements.add(new Placement("IBM","30th Jun","10:00am","Only CS and IS",R.drawable.ibm));
        placements.add(new Placement("HCL","29th Jul","03:00pm","Only CS , EC and IS",R.drawable.hcl));
        placements.add(new Placement("Flipkart","15th Jul","05:00pm","Only CS and IS",R.drawable.flipkart));
        placements.add(new Placement("Amazon","07th Aug","11:00am","Only CS and IS",R.drawable.amazon));
        placements.add(new Placement("Intel","14th Aug","01:00pm","Only CS , EC and IS",R.drawable.intel));
        placements.add(new Placement("Wipro","01th Nov","08:00am","All Branches",R.drawable.wipro));
    }
}

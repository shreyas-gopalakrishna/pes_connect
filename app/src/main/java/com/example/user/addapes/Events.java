package com.example.user.addapes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity {

    RecyclerView recyclerView;
    CardAdapter adapter;
    List<Dashboard> dashboards;

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

        adapter = new CardAdapter(dashboards);
        recyclerView.setAdapter(adapter);
    }
    private void initializeData() {

        dashboards = new ArrayList<>();
        dashboards.add(new Dashboard("A mesemerzing and enthralling concert by Salim-Suliamann,the famous Bollywood playback singers duo ",R.drawable.at1));
        dashboards.add(new Dashboard("A 24 hour hackathon conducted by the Cse department of Pesit to encourage budding coders and hackers",R.drawable.ayana));
        dashboards.add(new Dashboard("A fund raising  marathon organized by the Samarpana club for the brave martyrs of our country",R.drawable.run));
        dashboards.add(new Dashboard("A blood camp is being organized by Rotary Club of Pes this weekend.Do come to support this nobel initiative",R.drawable.blood));
        dashboards.add(new Dashboard("An event to bring out the entrepeurial skills in students",R.drawable.endeavour));
        dashboards.add(new Dashboard("Students from all departments showcase their  innovative projects.May the best department win",R.drawable.prakalpa));
        dashboards.add(new Dashboard("Hackathon conducted to test the thinking power of the student",R.drawable.hcode));
    }
}

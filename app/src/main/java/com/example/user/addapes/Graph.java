package com.example.user.addapes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    String id;
    private RequestHandler requestHandler;
    int a1;
    int a2;
    int a3;
    int a4;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        requestHandler = new RequestHandler();
        getData();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        int b1 = a1*100/total;
        int b2 = a2*100/total;
        int b3 = a3*100/total;
        int b4 = a4*100/total;

        System.out.println("total:a1:b1"+total+" "+a1+" "+b1);
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(b1, 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(b2, 1);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(b3, 2);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(b4, 3);
        valueSet1.add(v1e4);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Voted Options");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Option1");
        xAxis.add("Option2");
        xAxis.add("Option3");
        xAxis.add("Option4");
        return xAxis;
    }

    private void getData() {
        id = getIntent().getStringExtra("question");
        System.out.print("id sent is:\n"+id);
        class GetData extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                System.out.println("check0");
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                System.out.println("check2");
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerV2 rh = new RequestHandlerV2();
                String s = rh.sendGetRequestParam(Config2.URL_GET_EMP, id);
                System.out.print("String is:"+s);
                System.out.println("check1");
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }

    private void showData(String json) {

        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray result1 = jsonObj.getJSONArray(Config2.TAG_JSON_ARRAY);

            for (int i = 0; i < result1.length(); i++) {
                JSONObject c = result1.getJSONObject(i);
                String y1 = c.getString("q1");
                String y2 = c.getString("q2");
                String y3 = c.getString("q3");
                String y4 = c.getString("q4");

                a1=Integer.parseInt(y1);
                a2=Integer.parseInt(y2);
                a3=Integer.parseInt(y3);
                a4=Integer.parseInt(y4);
                total = a1+a2+a3+a4;
                System.out.println("total:" + total );
                System.out.println("y1:" + y1 +"y2:" + y2 +"y3:" + y3 +"y4:" + y4  + "\n");
            }
            BarChart chart = (BarChart) findViewById(R.id.chart);
            BarData data = new BarData(getXAxisValues(), getDataSet());
            chart.setData(data);
            chart.setDescription(" ");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
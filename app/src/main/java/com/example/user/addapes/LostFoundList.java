package com.example.user.addapes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LostFoundList extends AppCompatActivity {

    String id;
    private RequestHandler requestHandler;
    List<String> mobileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_list);
        requestHandler = new RequestHandler();
        getData();
    }

    private void getData() {
        id = "1";
        class GetData extends AsyncTask<Void, Void, String> {

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
                String s = rh.sendGetRequestParam(Config4.URL_GET_EMP, id);
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
            JSONArray result1 = jsonObj.getJSONArray(Config4.TAG_JSON_ARRAY);
            final List<String> mobileList=new ArrayList<String>();
            String finalo ;

            for (int i = 0; i < result1.length(); i++) {
                JSONObject c = result1.getJSONObject(i);
                String details = c.getString("descp");
                String arr[] = details.split(":");
                finalo = "Posted by : "+arr[0]+"\non "+arr[1]+":"+arr[2]+"\nLost Item : "+arr[3]+"\nDescription : "+arr[4]+"\nContact Info : "+arr[5];
                mobileList.add(finalo);
            }

            final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileList);
            ListView listView = (ListView) findViewById(R.id.mobile_list);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}


package com.example.user.addapes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class LostFound extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLost;
    private Button buttonFound;
    private Button buttonList;
    List<String> mobileList;
    String id;
    final Context context = this;

    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostfound);

        buttonLost = (Button) findViewById(R.id.buttonLost);
        buttonList = (Button) findViewById(R.id.buttonList);

        requestHandler = new RequestHandler();

        mobileList=new ArrayList<String>();

        buttonLost.setOnClickListener(this);
        buttonList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        LostFound.this,
                        LostFoundList.class);
                startActivity(i);
            }
        });

    }


    private void getData() throws UnsupportedEncodingException {
        //id = "Hello this is data of lost and found";
        int n = mobileList.size()-1;
        String data1;
        data1 = mobileList.get(n);
        System.out.print("String is:"+data1);
        byte[] data = data1.getBytes("UTF-8");
        id = Base64.encodeToString(data, Base64.DEFAULT);


        class GetData extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                System.out.println("check0");
                super.onPreExecute();
                // loading = ProgressDialog.show(MainActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                System.out.println("check2");
                //loading.dismiss();
                 }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerV2 rh = new RequestHandlerV2();
                System.out.print("String is:"+id);
                String s = rh.sendGetRequestParam(Config3.URL_GET_EMP, id);
                System.out.print("String is:"+s);
                System.out.println("check1");
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View msgView = inflater.inflate(R.layout.activity_user_dialog_lost, null);
        builder1.setView(msgView)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText tv = (EditText) (msgView).findViewById(R.id.Name);
                        String name = (tv.getText().toString());
                        EditText tv2 = (EditText) (msgView).findViewById(R.id.LostItem);
                        String lostItem = (tv2.getText().toString());
                        EditText tv3 = (EditText) (msgView).findViewById(R.id.Description);
                        String desc = (tv3.getText().toString());
                        EditText tv4 = (EditText) (msgView).findViewById(R.id.ContInfo);
                        String conc = (tv4.getText().toString());

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        String strDate = sdf.format(c.getTime());
                        //insert the below string into database
                        String details=name+":"+strDate+":"+lostItem+":"+desc+":"+conc;
                        mobileList.add(details);
                        System.out.print("String is:" + mobileList.size());
                        try {
                            getData();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                })
                .setNegativeButton("No,Sorry", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder1.create();
        alert.show();

    }
}
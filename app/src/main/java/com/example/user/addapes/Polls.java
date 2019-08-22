package com.example.user.addapes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Polls extends AppCompatActivity implements OnClickListener{

    private RadioGroup rgOpinion1;
    private RadioGroup rgOpinion2;
    private RadioGroup rgOpinion3;
    private Button submit1;
    private Button submit2;
    private Button submit3;
    private Button result1;
    private Button result2;
    private Button result3;
    private String id;
    private RequestHandler requestHandler;
    int pos;
    int ques;
    int sub1=1;
    int sub2=1;
    int sub3=1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);

        rgOpinion1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rgOpinion2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rgOpinion3 = (RadioGroup) findViewById(R.id.radioGroup3);
        submit1 = (Button) findViewById(R.id.button1s);
        submit2 = (Button) findViewById(R.id.button2s);
        submit3 = (Button) findViewById(R.id.button3s);
        result1 = (Button) findViewById(R.id.button1r);
        result2 = (Button) findViewById(R.id.button2r);
        result3 = (Button) findViewById(R.id.button3r);

        requestHandler = new RequestHandler();

        submit1.setOnClickListener(this);
        submit2.setOnClickListener(this);
        submit3.setOnClickListener(this);

        result1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Polls.this,
                        Graph.class);
                i.putExtra("question","1");
                startActivity(i);
            }
        });
        result2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Polls.this,
                        Graph.class);
                i.putExtra("question","2");
                startActivity(i);
            }
        });
        result3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Polls.this,
                        Graph.class);
                i.putExtra("question","3");
                startActivity(i);
            }
        });

    }

    public void onClick(View v) {

        if (v == submit1 && sub1 == 1) {
            pos = rgOpinion1.indexOfChild(findViewById(rgOpinion1.getCheckedRadioButtonId()));
            System.out.println("Selected Opinion for q1 is :" + pos );
            if(pos == -1)
            {
                Toast.makeText(this,"Select an option", Toast.LENGTH_LONG).show();
            }
            else
            {
                sub1=0;
                ques = 1;
                pos=pos+1;
                getData();
            }
        }else if (v == submit2 && sub2 == 1) {
            pos = rgOpinion2.indexOfChild(findViewById(rgOpinion2.getCheckedRadioButtonId()));
            System.out.println("Selected Opinion for q2 is :" + pos );
            if(pos == -1)
            {
                Toast.makeText(this,"Select an option", Toast.LENGTH_LONG).show();
            }
            else
            {
                sub2=0;
                ques = 2;
                pos=pos+1;
                getData();
            }
        }else if (v == submit3 && sub3 == 1) {
            pos = rgOpinion3.indexOfChild(findViewById(rgOpinion3.getCheckedRadioButtonId()));
            System.out.println("Selected Opinion for q3 is :" + pos );
            if(pos == -1)
            {
                Toast.makeText(this,"Select an option", Toast.LENGTH_LONG).show();
            }
            else
            {
                sub3=0;
                ques = 3;
                pos=pos+1;
                getData();
            }
        }
        else if(sub1 == 0 || sub2 == 0 ||sub3==0)
        {
            Toast.makeText(this,"Already Submited", Toast.LENGTH_LONG).show();
        }
    }

    private void getData() {
        id = ques+"_"+pos;
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
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerV2 rh = new RequestHandlerV2();
                System.out.println("Sent ID :" + id );
                String s = rh.sendGetRequestParam(Config1.URL_GET_EMP, id);
                System.out.print("String is:"+ s);
                System.out.println("check1");
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }
}

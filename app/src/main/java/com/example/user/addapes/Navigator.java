package com.example.user.addapes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Navigator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String usn = getIntent().getStringExtra("USN");
        System.out.println("usn" + usn);

        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Navigator.this,
                        Timetable.class);
                i.putExtra("USN", usn);
                startActivity(i);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        CampusMart.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        Events.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        PlaceActivity.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        Doubts.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        Polls.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        LostFound.class);
                i.putExtra("USN",usn);
                startActivity(i);
            }
        });

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(
                        Navigator.this,
                        SplashScreen.class);
                startActivity(i);
            }
        });
    }
}


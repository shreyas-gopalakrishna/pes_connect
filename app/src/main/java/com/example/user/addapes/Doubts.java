package com.example.user.addapes;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Doubts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubts);
        final String usn = getIntent().getStringExtra("USN");
        System.out.println("usn" + usn);

        String[] mobileArray = {" Prof.Srinivas Murthy ", "Prof.Channa Bankapur", "Badri Sir ", "Nithin Pujari Sir ", "Srikanth Sir", "Prafullata Maam", "Prof.Nsk", "Nagraj Sir"};

        final Context context = this;


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                final String item = ((TextView) view).getText().toString();

                String phoneNumber = "";
                if (id == 0 || id == 1 || id == 2 || id == 3) {
                    phoneNumber = "9164554330";
                } else {
                    phoneNumber = "9008803126";
                }
                final String phoneNo = phoneNumber;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to contact "+item+" ?")
                        .setCancelable(false)
                        .setPositiveButton("Yup", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // fire an intent go to your next activity
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                // Get the layout inflater
                                //LayoutInflater inflater = getActivity().getLayoutInflater();
                                LayoutInflater inflater = LayoutInflater.from(context);

                                // Inflate and set the layout for the dialog
                                // Pass null as the parent view because its going in the dialog layout
                                final View msgView = inflater.inflate(R.layout.activity_user_dialog, null);
                                builder1.setView(msgView)
                                        .setPositiveButton("Yes,I want to contact", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                // sign in the user ...
                                                //Here create another dialog box and send the message.....
                                                //change the phone number accordingly
                                                EditText tv = (EditText) (msgView).findViewById(R.id.message);
                                                System.out.println("First");
                                                String doubt = (tv.getText().toString());
                                                System.out.println("Second");


                                                try {

                                                    SmsManager.getDefault().sendTextMessage(phoneNo, null, usn+"\n"+doubt, null, null);
                                                    Toast.makeText(getBaseContext(), "Your message was sent succesfully! Your query will be answered shortly", Toast.LENGTH_LONG).show();
                                                } catch (Exception e) {
                                                    android.app.AlertDialog.Builder alertDialogBuilder = new
                                                            android.app.AlertDialog.Builder(context);
                                                    android.app.AlertDialog dialog1 = alertDialogBuilder.create();


                                                    dialog1.setMessage(e.getMessage());
                                                    dialog1.show();


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

                        })

                        .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        }


    }


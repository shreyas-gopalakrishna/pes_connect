package com.example.user.addapes;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CampusMart extends AppCompatActivity {

    // Todo: Don't hardcore. Retrieve from a file, database or server
    // Different subject notes
    public String[] mobileArray = {"Fafl notes ","Operating Systems","Environmental Studies ","Imp ","Material design","Software engineering","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmart);

        final Context context=this;
        final List<String> orders= new ArrayList<String>();
        final List<String> submittedOrders=new ArrayList<String>();

        final String usn = getIntent().getStringExtra("USN");
        System.out.println("usn" + usn);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                final String item = ((TextView) view).getText().toString();

                // dialog to prompt user to add notes and proceed
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to add these notes to the cart?")
                        .setCancelable(false)
                        .setPositiveButton("Yup", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // fire an intent go to your next activity
                                orders.add(item);
                                Toast.makeText(getBaseContext(),item +"notes were added to cart", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                // Toast.makeText(getBaseContext(), norder, Toast.LENGTH_LONG).show();

            }
        });

        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                           String finalOrder = "";
                                           for (int i = 0; i < orders.size(); i++) {
                                               finalOrder = finalOrder.concat((i + 1) + "."+ orders.get(i) + "\n");
                                           }


                                           builder.setMessage("Are you sure you want to place  orders for following notes?"+"\n"+finalOrder)
                                                   .setCancelable(false)
                                                   .setPositiveButton("Yes, lets do this", new DialogInterface.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int id) {
                                                           // fire an intent go to your next activity

                                                           Toast.makeText(getBaseContext(),"Thank you,Your order has been placed.Collect the notes after sometime:-)", Toast.LENGTH_LONG).show();

                                                           try {
                                                               String phoneNumber="9164554330";
                                                               String finalOrder1 = "";
                                                               for (int i = 0; i < orders.size(); i++) {
                                                                   finalOrder1 = finalOrder1.concat((i + 1) + ". "+ orders.get(i) + "\n");
                                                               submittedOrders.add(finalOrder1);
                                                               }
                                                               orders.clear();
                                                               SmsManager.getDefault().sendTextMessage(phoneNumber, null,usn+"\norders for \n"+finalOrder1, null, null);
                                                           } catch (Exception e) {
                                                               android.app.AlertDialog.Builder alertDialogBuilder = new
                                                                       android.app.AlertDialog.Builder(context);
                                                               android.app.AlertDialog dialog1 = alertDialogBuilder.create();

                                                               dialog1.setMessage(e.getMessage());
                                                               dialog1.show();

                                                           }

                                                       }
                                                   })
                                                   .setNegativeButton("Err, no", new DialogInterface.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int id) {
                                                           orders.clear();
                                                           dialog.cancel();
                                                       }
                                                   });
                                           AlertDialog alert = builder.create();
                                           alert.show();
                                       }
                                   }

        );

    }
}





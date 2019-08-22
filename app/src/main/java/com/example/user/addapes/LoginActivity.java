package com.example.user.addapes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shreyas on 18-Mar-16.
 */

public class LoginActivity extends AppCompatActivity implements AsyncResponse{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    String result = new String("SloNetwork");
    int i=0;


    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                result="SloNetwork";
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                intent.putExtra("USN", _emailText.getText().toString());
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type,email, password);

       new android.os.Handler().postDelayed(
               new Runnable() {
                   public void run() {
                       // On complete call either onLoginSuccess or onLoginFailed
                       if (result.equals("")) {
                           System.out.println("Not here1");
                           //waitForIt(progressDialog);
                           onLoginFailedV2();
                           progressDialog.dismiss();
                       } else if (result.equals("SlowNetwork")) {
                           System.out.println("Not here2");
                           waitForIt(progressDialog);

                       } else if (result.equals("Login Success")) {
                           onLoginSuccess();
                           progressDialog.dismiss();
                       } else {
                           System.out.println("Why come here1");
                           onLoginFailed();
                           progressDialog.dismiss();
                       }
                       // onLoginFailed();

                   }
               }, 3000);

    }

    protected void waitForIt(final ProgressDialog progressDialog) {

        if (i >= 5) {
            onLoginFailedV3();
            progressDialog.dismiss();
        } else {
            i++;
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            if (result.equals("")) {
                                System.out.println("Infinite Loop 1");
                                onLoginFailedV2();
                                progressDialog.dismiss();
                            }
                            else if(result.equals("SloNetwork")){
                                System.out.println("Infinite Loop 2");
                                progressDialog.setMessage("Authenticating...Slow Network connection");
                                waitForIt(progressDialog);

                            }
                            else if (result.equals("Login Success")) {

                                onLoginSuccess();
                                progressDialog.dismiss();
                            } else {
                                System.out.println("Why come here2");
                                onLoginFailed();
                                progressDialog.dismiss();
                            }
                            // onLoginFailed();

                        }
                    }, 5000);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                              // this.finish();
                Intent p = new Intent(LoginActivity.this, Navigator.class);
                p.putExtra("USN",_emailText.getText());
                startActivity(p);
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        i=0;
        result="sloNetwork";
        _loginButton.setEnabled(true);
        Intent p = new Intent(LoginActivity.this, Navigator.class);
        p.putExtra("USN",_emailText.getText().toString());
        startActivity(p);

       // finish();
    }
    public void processFinish(String output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        result=output;
        System.out.println("The final attempt in LoginActivity is" + output);

    }

    public void onLoginFailed() {
        i=0;
        result="SloNetwork";
        Toast.makeText(getBaseContext(), "Login failed due to incorrect Username or Password", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }
    public void onLoginFailedV2() {
        i=0;
        result="SloNetwork";
        Toast.makeText(getBaseContext(), "No Network", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }
    public void onLoginFailedV3() {
        i=0;
        result="SloNetwork";
        Toast.makeText(getBaseContext(), "Extremely Slow Network", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();



        if (email.isEmpty() || email.length()> 10 || email.charAt(0) != '1'|| email.charAt(1) != 'P' || email.charAt(2) != 'I' ) {
            _emailText.setError("enter a valid USN");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}

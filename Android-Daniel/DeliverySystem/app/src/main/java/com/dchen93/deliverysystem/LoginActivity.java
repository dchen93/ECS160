package com.dchen93.deliverysystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends ActionBarActivity implements OnClickListener {

    private EditText user, password;
    private Button mLogin, mRegister;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Setup input fields
        user = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        // Setup buttons
        mLogin = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if(user.getText().length()>0)
                {
                    new LoginAttempt().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter a username & password before continuing", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.register:
                if(user.getText().length()>0)
                {
                    new RegisterAttempt().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter a username & password before continuing", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(0xff2979ff));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    class LoginAttempt extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //setup progress dialog
//            progressDialog = new ProgressDialog(LoginActivity.this);
//            progressDialog.setMessage(getString(R.string.progress_login));
//            progressDialog.setIndeterminate(true);
//            progressDialog.setCancelable(true);
//            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            /** TO-DO:
             * communicate with server, parse JSON */

            //if there's a match
            //if(true){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                finish();
                LoginActivity.this.startActivity(intent);
            //}

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
//            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Hello " + user.getText().toString() + "!", Toast.LENGTH_SHORT).show();
        }
    }

    class RegisterAttempt extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //setup progress dialog
//            progressDialog = new ProgressDialog(LoginActivity.this);
//            progressDialog.setMessage(getString(R.string.progress_register));
//            progressDialog.setIndeterminate(true);
//            progressDialog.setCancelable(true);
//            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            /** TO-DO:
             * communicate with server, parse JSON */
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
//            progressDialog.dismiss();
            Toast toast = Toast.makeText(getApplicationContext(), R.string.success_register, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 780);
            toast.show();
        }
    }
}

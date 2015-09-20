package com.website.tourapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    //Define your variables here
    private EditText ET_PhoneNumber;
    private static String phoneNumber;

    //Define a local file in which we save user's details
    public static final String CONFIGFILE = "user.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Display the welcome screen
        this.setContentView(R.layout.activity_welcome);

        //new ContactServlet().execute(new Pair<Context, String>(this, "awesome person"));

        //Look if the app already has a phone number
        if (fileExists(this,CONFIGFILE)){
            deleteFile(CONFIGFILE);
        }
        if (fileExists(this,CONFIGFILE)){
            Log.d("Location","file exists");
        }
        else{
            Log.d("Location", "Asking for the mobile number");
            Intent i = new Intent(getApplicationContext(), RegisterPhone.class);
            startActivity(i);


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void phone_number_clicked(View view){
        // do something here
        ET_PhoneNumber = (EditText)findViewById(R.id.user_phone_number_editText);
        phoneNumber = ET_PhoneNumber.getText().toString();

        //setContentView(R.layout.user_form);
        Log.d("phone number", phoneNumber);
        new QueryServlet().execute(new Pair<Context,String>(this,phoneNumber));
    }

    //a function to check if a file already exists in the local system
    public boolean fileExists(Context context, String fileName){
        File file = context.getFileStreamPath(fileName);
        if(file == null || !file.exists()){

            return  false;
        }
        return true;

    }




}

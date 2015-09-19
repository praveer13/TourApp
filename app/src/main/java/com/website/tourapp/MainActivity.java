package com.website.tourapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Define your variables here
    private EditText ET_PhoneNumber;
    private static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Status", "1");
        new ContactServlet().execute(new Pair<Context, String>(this, "awesome person"));
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
        ET_PhoneNumber = (EditText)findViewById(R.id.user_phone_number);
        phoneNumber = ET_PhoneNumber.getText().toString();

        //setContentView(R.layout.user_form);
        Log.d("phone number", phoneNumber);
        new QueryServlet().execute(new Pair<Context,String>(this,phoneNumber));

    }
}

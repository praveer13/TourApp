package com.website.tourapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

/**
 * Created by Sourabh.Gupta1 on 21-09-2015.
 */
public class AddMembersActivity extends Activity {
    AutoCompleteTextView add_member_ACTV;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //set the add member layout
        Log.d("Location", "0");
        setContentView(R.layout.add_members_layout);
        Log.d("Location", "1");
        add_member_ACTV = (AutoCompleteTextView)findViewById(R.id.add_member_autocomTV);
        //Add names here
        String[] names = {"Sourabh", "Prakash", "Gupta"};
        Log.d("Location", "2");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,names);
        add_member_ACTV.setThreshold(1);
        add_member_ACTV.setAdapter(adapter);

        //add an event onclick on the add_member_ACTV
        /*add_member_ACTV.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //get the value of the select item
                Log.d("Location", "6");
                String selectedItem =  parent.getItemAtPosition(position).toString().trim();
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
                Log.d("selectedItem", selectedItem);

        });
        */
        //add an event onclick on the add_member_ACTV
        add_member_ACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });


    }


}

package com.website.tourapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class RegisterPhone extends Activity{

    //Define all the view elements here
    Spinner spinner;
    Button  next_button;
    EditText phone_editText;


    @Override
    protected void onCreate(Bundle savedInstanceState){




        super.onCreate(savedInstanceState);

        //display the phone register screen
        this.setContentView(R.layout.register_phone);

        //Add a list of countries in the country list spinner
        String[] countries = {"India", "Canada", "England","Russia"};
        //Attach the list of countries to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);

        //Specify the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner)findViewById(R.id.CountryList_spinner);
        spinner.setAdapter(adapter);

        //set a onClick() event listener to the next button on this screen

    }
    public void phone_next_number_signup_clicked(View view){
        //Get the country
        //to be implemented

        //Get the phone number

        next_button = (Button)findViewById(R.id.next_phone_signup);
        phone_editText = (EditText)findViewById(R.id.user_phone_number_editText);
        String phoneNumber = phone_editText.getText().toString();

        String country = spinner.getSelectedItem().toString();
        //
        if (phoneNumber.trim().length() ==10 && country.trim() == "India"){
            new QueryServlet().execute(new Pair<Context,String>(this,phoneNumber.trim()));
        }
        else{
            showToast("number not right");
        }
    }

    //a method for displaying toasts
    public void showToast(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,message,duration);
        toast.show();
    }
}

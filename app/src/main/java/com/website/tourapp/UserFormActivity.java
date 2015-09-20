package com.website.tourapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class UserFormActivity extends Activity {
    String phoneNumber;
    String firstName;
    String lastName;

    //set the view elements here
    EditText userFirstName_EditText;
    EditText userLastName_EditText;
    Spinner city_spinner;

    @Override
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //display the user form screen
        this.setContentView(R.layout.user_form);

        //Get the phone number put by the RegisterPhone class
        phoneNumber = getIntent().getExtras().getString("phoneNumber");
        new RegisterPhone().showToast(this,phoneNumber);

        //Initialize the view elements
        userFirstName_EditText = (EditText)findViewById(R.id.user_first_name);
        userLastName_EditText = (EditText)findViewById(R.id.user_last_name);

        String[] city = {"Bangalore", "Delhi", "Mumbai", "Chennai"};
        //Attach the list of countries to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, city);

        //Specify the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner = (Spinner)findViewById(R.id.city_spinner);
        city_spinner.setAdapter(adapter);



    }

    public  void user_details_clicked(View view){
        firstName = userFirstName_EditText.getText().toString().trim();
        lastName  = userLastName_EditText.getText().toString().trim();

        if (firstName.length() != 0 && lastName.length() !=  0){
            //Toast hello....
            String toastMessage = "Hello " + firstName + " " + lastName + "!";
            Toast.makeText(this,toastMessage,Toast.LENGTH_LONG).show();

            //Store his details on database
            new MakeAccountClient(this).execute(firstName,lastName,phoneNumber);


        }
        else{
            //Toast to fill the fields correctly
            Toast.makeText(this,"Invalid names", Toast.LENGTH_SHORT).show();
        }

    }
}


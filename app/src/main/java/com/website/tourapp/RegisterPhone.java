package com.website.tourapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class RegisterPhone extends Activity{

    //Define all the view elements here
    Spinner spinner;
    Button  next_button;
    EditText phone_editText;
    String phoneNumber;

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
        phoneNumber = phone_editText.getText().toString().trim();

        String country = spinner.getSelectedItem().toString().trim();
        //
        if (phoneNumber.length() ==10 && country == "India"){
            Log.d("phoneNumber", phoneNumber);
            new UserExistsAsync(this).execute(new Pair<Context, String>(this, phoneNumber));
            //String fileExists = new UserExistsAsync().doInBackground(new Pair<Context, String>(this, phoneNumber));

        }
        else{
            showToast(this,"number not right");
        }
    }

    //a method for displaying toasts
    public void showToast(Context context, String message){

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    //a method to be invoked
    public void onResult( Context context, String response){
        //Convert the response to an int
        int count = Integer.parseInt(response);

        if (count ==0){
            //Ask the user for his details like first name, last name etc
            showToast(context,"Welcome aboard! " );

            //Take the user to user_form
            Intent intent = new Intent(context,UserFormActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);


        }
        else if(count ==1){
            //Take him to his/her page
            showToast(context,"It's been a while! " );

            //look for the file CONFIGFILE
            if (readFromFile(MainActivity.CONFIGFILE) != null){
                Intent intent = new Intent(context,GroupsActivity.class );
                startActivity(intent);

            }

        }
        else{
            showToast(context,response + response.length() + Integer.toString(1));
        }
    }

    //A method for reading a file
    public  String[] readFromFile(String fileName){
        String[] data = new String[3];

        try{
            InputStream inputStream = openFileInput(fileName);
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String recievedString  = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((recievedString = bufferedReader.readLine()) != null  ){
                    stringBuilder.append(recievedString);
                }
                inputStream.close();
                data = stringBuilder.toString().split(",");
                return  data;
            }
            else{
                return  null;
            }

        }
        catch (Exception e){
            Log.d("Error", e.getMessage() + "," + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return  data;
    }
}

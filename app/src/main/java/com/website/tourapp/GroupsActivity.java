package com.website.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class GroupsActivity extends Activity {

    String userDetails;
    String firstName;
    String lastName;
    String phoneNumber;

    String[] userDetailsArr;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //set the groups_layout
        setContentView(R.layout.groups_layout);

        //Read the USERFILE for his details
        userDetailsArr =  readFromFile(MainActivity.CONFIGFILE);

        phoneNumber = userDetailsArr[0];
        firstName = userDetailsArr[1];
        lastName = userDetailsArr[2];




    }

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
            }
        }
        catch (Exception e){
            Log.d("Error", e.getMessage() + "," + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return  data;
    }


    public void create_group_button_Clicked(View view){

        Intent intent = new Intent(this,CreateGroupActivity.class);
        startActivity(intent);


    }
}

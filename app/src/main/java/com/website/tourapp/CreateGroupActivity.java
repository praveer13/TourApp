package com.website.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class CreateGroupActivity extends Activity{

    EditText new_group_name_editText;
    @Override
    public void onCreate(Bundle onSavedInstance){
        super.onCreate(onSavedInstance);

        //Set the create_group_layout
        setContentView(R.layout.create_group_layout);
    }

    public void new_group_button_Clicked(View view){
        new_group_name_editText =  (EditText)findViewById(R.id.new_group_name_editText);
        String newGroupName = new_group_name_editText.getText().toString().trim();

        if (newGroupName.length() !=0){
            Toast.makeText(this,"Add members", Toast.LENGTH_SHORT).show();

            //Take the user to add member page
            Intent intent = new Intent(this,AddMembersActivity.class);
            intent.putExtra("groupName", newGroupName);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"empty name", Toast.LENGTH_SHORT).show();
        }
    }
}

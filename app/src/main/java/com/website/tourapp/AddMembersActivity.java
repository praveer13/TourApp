package com.website.tourapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.provider.ContactsContract;
import android.database.Cursor;

import java.util.ArrayList;


/**
 * Created by Sourabh.Gupta1 on 21-09-2015.
 */
public class AddMembersActivity extends Activity {
    AutoCompleteTextView add_member_ACTV;
    ListView member_listView;
    final ArrayList<String> members = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //set the add member layout
        Log.d("Location", "0");
        setContentView(R.layout.add_members_layout);
        Log.d("Location", "1");
        add_member_ACTV = (AutoCompleteTextView)findViewById(R.id.add_member_autocomTV);

        //Add names here
        String[] names = getAllNames(this.getContentResolver());

        Log.d("Location", "2");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,names);
        add_member_ACTV.setThreshold(1);
        add_member_ACTV.setAdapter(adapter);

        final ArrayAdapter<String> memberAdapter;
        memberAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,members);
        member_listView = (ListView)findViewById(R.id.members_listView);


        //add an event onclick on the add_member_ACTV
        add_member_ACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //clear the text
                add_member_ACTV.setText(null);
                //add items to the member_list
                addMember(selectedItem, memberAdapter);

                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });





    }

    //Show a member in the members_listview if user selects them
    public void addMember(String name, ArrayAdapter<String> adapter){
        //add the name in the final members string arraylist
        if (members.contains(name) == false){
            members.add(name);
        }
        else{
            Toast.makeText(getApplicationContext(), "Already selected", Toast.LENGTH_LONG).show();
        }

        member_listView.setAdapter(adapter);

    }


    //A method to fetch the contacts
    public String[] getAllNumbers(ContentResolver contentResolver){

        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null, null);
        int count = cursor.getCount();
        String[] numbers =  new String[count];
        String number;
        String name;
        int i =0;

        //add all the numbers
        while (cursor.moveToNext()){
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
           // name =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            numbers[i] = number;
            Log.d("Number", number);
            i++;
        }
        return numbers;
    }

    //Get all the names
    public String[] getAllNames(ContentResolver contentResolver){

        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null, null);
        int count = cursor.getCount();
        String[] names =  new String[count];
        String name;
        int i =0;

        //add all the numbers
        while (cursor.moveToNext()){
            name =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            names[i] = name;
            Log.d("Name", name);
            i++;
        }
        return  names;
    }


}

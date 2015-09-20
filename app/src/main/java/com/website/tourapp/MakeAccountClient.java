package com.website.tourapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class MakeAccountClient extends AsyncTask<String,String,String> {

    Context context;

    //Constructor for this class

    public MakeAccountClient(Context c){
        this.context = c;
    }

    @Override
    public String doInBackground(String...param){
        String firstName = param[0];
        String lastName = param[1];
        String mobileNumber = param[2];
        String response;

        try{
            URL url = new URL("http://www.tourapp-1071.appspot.com/MakeAccountServlet");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

            out.write("firstName="+firstName + "\r\n");
            out.write("&lastName="+lastName +"\r\n");
            out.write("&mobileNumber="+mobileNumber + "\r\n");
            out.flush();
            out.close();

            //take the response of the servlet
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()  ));
            response = in.readLine();

        }
        catch (Exception e){
            response = "Error,MakeAccountClient" + e.getMessage();
        }
        return  response;
    }

    @Override
    public void onPostExecute(String result){
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}

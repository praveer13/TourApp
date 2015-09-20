package com.website.tourapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */

public class UserExistsAsync  extends AsyncTask<Pair<Context,String>,Void,String>{
    private Context context;

    @Override
    public   String doInBackground(Pair<Context, String>...param){
        context = param[0].first;
        String phoneNumber = param[0].second;
        String response;

        //Try a connection with the Servlet
        try{
            URL url = new URL("http://www.tourapp-1071.appspot.com/UserExistsServlet");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

            out.write("mobileNumber=" + phoneNumber);
            out.flush();
            out.close();

            //take the response of the servlet
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()  ));
            response = in.readLine();
        }
        catch (Exception e){
            response = "Error," + "UserExistsAsync," +  e.getMessage();
            e.printStackTrace();
        }
        Log.d("Response, ", response.trim());
        return  response.trim();

    }

    @Override
    public void onPostExecute(  String result){
        RegisterPhone registerPhone = new RegisterPhone();
        registerPhone.onResult(context, result);
    }
}

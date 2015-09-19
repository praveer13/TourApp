package com.website.tourapp;


/**
 * Created by Sourabh.Gupta1 on 19-09-2015.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.*;
import java.net.*;

public class ContactServlet extends AsyncTask<Pair<Context,String>,Void,String>{

    private  Context context;

    @Override
    protected String doInBackground(Pair<Context,String>...params){
        context = params[0].first;
        String name = params[0].second;
        Log.d("name", name);
        Log.d("Status", "2");
        try{
            URL url = new URL("http://www.tourapp-1071.appspot.com/hello");
            //URL url = new URL("http://www.tourapp-.appspot.com/hello");
            Log.d("Status", "2.5");
            URLConnection conn = url.openConnection();
            Log.d("Status", "2.6");
            conn.setDoOutput(true);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            Log.d("Status", "2.7");
            out.write("user=" + name  + "\r\n");
            out.flush();
            out.close();
            Log.d("Status", "2.8");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.d("Status", "2.9");
            String response = in.readLine() ;
            while(response!= null){
                Log.d("Status", "3");
                in.close();
                Log.d("response",  response);

                return  response;

            }


            in.close();
            return "no value";
        }
        catch(MalformedURLException e){

            Log.d("Status", "4");
            return  e.getMessage();
        }
        catch (IOException e){
            Log.d("Status", "5" + e.getMessage() );
            return  e.getMessage();
        }


    }
    @Override
    protected void onPostExecute(String result){
        Log.d("Status", "6");
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}

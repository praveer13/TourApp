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

public class QueryServlet  extends  AsyncTask<Pair<Context,String>,Void,String>{
    private  Context context;

    @Override
    protected String doInBackground(Pair<Context,String>...params){
        context = params[0].first;
        String name = params[0].second;
        Log.d("name", name);
        Log.d("Status", "2");
        try{
            URL url = new URL("http://www.tourapp-1071.appspot.com/userDetailsServlet");

            Log.d("Status", "2.5");
            URLConnection conn = url.openConnection();
            Log.d("Status", "2.6");
            conn.setDoOutput(true);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            Log.d("Status", "2.7");
            out.write("mobileNumber=" + name  + "\r\n");
            out.flush();
            out.close();
            Log.d("Status", "2.8");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.d("Status", "2.9");
            String response = in.readLine() ;
            //Log.d("response",  response);
            while(response!= null){
                Log.d("Status", "3");

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
        catch (Exception e){
            Log.d("Exception", e.getMessage());
            e.printStackTrace();
            return  e.getMessage();
        }


    }
    @Override
    protected void onPostExecute(String result){
        Log.d("Resulr", result );
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

package com.website.backend.backend_servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sourabh.Gupta1 on 20-09-2015.
 */
public class UserExistsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException{

        //Define the variables here
        String url = "jdbc:google:mysql://tourapp-1071:tour-sql-instance/tour_db";
        PrintWriter out = response.getWriter();

        try{
            Class.forName("com.mysql.jdbc.GoogleDriver");
        }
        catch (Exception e){
            e.printStackTrace();
            out.println("Error," + e.getMessage());
        }

        try{
            //Connection with the database
            Connection connection = DriverManager.getConnection(url, "root", "");

            try{
                String phoneNumber = request.getParameter("mobileNumber").trim();

                //Query for checking existance of number in our database
                String query = "select count(MobileNumber) as count from tour_db.userDetails where MobileNumber=?";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,phoneNumber);
                ResultSet rs = statement.executeQuery();

                //Check if the count is more than 0
                while(rs.next()){
                    String count = rs.getString("count");
                    out.println(count);
                }
            }
            finally {
                connection.close();
            }
        }
        catch (Exception e){
            out.println("Error,UserExistsServlet,"  + e.getMessage());
        }
    }
}

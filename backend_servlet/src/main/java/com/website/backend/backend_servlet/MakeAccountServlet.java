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
public class MakeAccountServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
            Connection connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(false);
            try{
                String phoneNumber = request.getParameter("mobileNumber");
                String firstName  = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");

                //query for uploading the details to UserDetails
                String query = "insert into tour_db.userDetails (FirstName, LastName, MobileNumber) values(?,?,?)";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,firstName);
                statement.setString(2,lastName);
                statement.setString(3,phoneNumber);

                int i = statement.executeUpdate();
                connection.commit();

                out.println(i);
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
                out.println(e.getMessage());
        }
    }
}

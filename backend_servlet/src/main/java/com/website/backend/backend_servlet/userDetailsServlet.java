package com.website.backend.backend_servlet;

/**
 * Created by Sourabh.Gupta1 on 19-09-2015.
 */

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
//import com.google.appengine.api.utils.SystemProperty;

public class userDetailsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException{
        String url = null;

        try{
            Class.forName("com.mysql.jdbc.GoogleDriver");
            url = "jdbc:google:mysql://tourapp-1071:tour-sql-instance/tour_db";


        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }
        PrintWriter out = response.getWriter();

        try{
            Connection conn = DriverManager.getConnection(url,"root", "");
            try{
                String mobileNumber = request.getParameter("mobileNumber");
                if(mobileNumber.trim() == ""){
                    out.println("no number");
                    return;


                }

                String query = "select * from tour_db.userDetails where MobileNumber = ?";

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, mobileNumber.trim());
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    out.println(rs.getString("FirstName") );

                    return;
                }



            }
            finally {
                conn.close();
            }

        }

        catch (SQLException e ){
            out.println("error sql" + e.getMessage());
            e.printStackTrace();
        }

    }


}


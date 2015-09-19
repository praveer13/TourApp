/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.website.backend.backend_servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import sun.rmi.runtime.Log;

public class MyServlet extends HttpServlet {
    private  static final String userName = "user";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter(userName);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Hello "+ name);
    }
}

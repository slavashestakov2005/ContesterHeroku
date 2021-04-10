package com.example.runner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/run")
public class Run extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        new RunQuery(request, response).execute();
    }
}
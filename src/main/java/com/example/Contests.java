package com.example;

import com.example.database.rows.Contest;
import com.example.database.tables.ContestsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/contests")
public class Contests extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int id = Integer.parseInt(request.getParameter("id"));
        String tName = request.getParameter("t_name");
        String tAbout = request.getParameter("t_about");
        long start = Long.parseLong(request.getParameter("start"));
        long finish = Long.parseLong(request.getParameter("finish"));
        String password = request.getParameter("password");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.write(status);
        if (status.equals("Ok")){
            ContestsTable.updateContestByID(new Contest(id, tName, tAbout, start, finish, password));
        }
    }
}

package com.example;

import com.example.database.rows.Test;
import com.example.database.tables.TestsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/tests")
public class Tests extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String input = request.getParameter("input");
        String output = request.getParameter("output");
        boolean isExample = Boolean.parseBoolean(request.getParameter("example"));
        boolean isPublic = Boolean.parseBoolean(request.getParameter("public"));
        int id = Integer.parseInt(request.getParameter("test"));
        int task = Integer.parseInt(request.getParameter("task"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.print(status);
        if (status.equals("Ok")){
            if (id == -1) TestsTable.add(new Test(task, input, output, isExample, isPublic));
            else TestsTable.update(new Test(id, task, input, output, isExample, isPublic));
        }
    }
}

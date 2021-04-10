package com.example;

import com.example.database.rows.Task;
import com.example.database.tables.TasksTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks")
public class Tasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int id = Integer.parseInt(request.getParameter("id"));
        String tName = request.getParameter("t_name");
        String tAbout = request.getParameter("t_about");
        String tInput = request.getParameter("t_input");
        String tOutput = request.getParameter("t_output");
        String tSolution = request.getParameter("t_solution");
        int tTimeLimit = Integer.parseInt(request.getParameter("t_time"));
        int tMemoryLimit = Integer.parseInt(request.getParameter("t_memory"));
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.write(status);
        if (status.equals("Ok")){
            TasksTable.updateTaskByID(new Task(id, tName, tAbout, tInput, tOutput, tSolution, tTimeLimit, tMemoryLimit));
        }
    }
}

package com.example;

import com.example.database.rows.Contest;
import com.example.database.rows.Task;
import com.example.database.tables.ContestsTable;
import com.example.generator.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet("/add_contest")
public class AddContest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String cName = request.getParameter("c_name");
        String cAbout = request.getParameter("c_about");
        long start = Long.parseLong(request.getParameter("start"));
        long finish = Long.parseLong(request.getParameter("finish"));
        String password = request.getParameter("password");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.print(status);
        if (status.equals("Ok")){
            Contest contest = new Contest(cName, cAbout, start, finish, password);
            int id = ContestsTable.add(contest);
            contest = new Contest(id, cName, cAbout, start, finish, password);
            ArrayList<Contest> contests = ContestsTable.getAll();
            ContestsSidebarGenerator.generate(contests);
            StartPageGenerator.generate(contest);
            /***/ContesterPageGenerator.generate();
            Files.createDirectories(Paths.get(Root.webDirectory +  "\\" + id));
            MainPageGenerator.generate(contest);
            ArrayList<Task> tasks = new ArrayList<>();
            TasksSidebarGenerator.generate(contest, tasks);
        }
    }
}

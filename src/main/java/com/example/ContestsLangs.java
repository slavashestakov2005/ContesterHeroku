package com.example;

import com.example.database.rows.ContestLang;
import com.example.database.tables.ContestsLangsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/contests_langs")
public class ContestsLangs extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        int contest = Integer.parseInt(request.getParameter("contest"));
        int lang = Integer.parseInt(request.getParameter("lang"));
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
            ContestsLangsTable.add(new ContestLang(contest, lang));
        }
    }
}

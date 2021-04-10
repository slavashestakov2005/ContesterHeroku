package com.example;

import com.example.database.rows.Sending;
import com.example.database.tables.LangsTable;
import com.example.database.tables.SendingsTable;
import com.example.runner.Status;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/view_code")
public class ViewCode extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        int task = Integer.parseInt(request.getParameter("task"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        ArrayList<Sending> sendings = SendingsTable.selectAllForUser(surname + " " + name);
        pw.print("<h2><center>Посылки:</center></h2>\n" +
                "<table border=\"1\" width=\"95%\">\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"15%\"><center>Дата</center></td>\n" +
                "\t\t<td width=\"10%\"><center>Язык</center></td>\n" +
                "\t\t<td width=\"10%\"><center>Результат</center></td>\n" +
                "\t\t<td width=\"10%\"><center>Тест</center></td>\n" +
                "\t\t<td width=\"55%\"><center>Код</center></td>\n" +
                "\t</tr>\n");
        for(Sending sending : sendings) {
            pw.print("\t<tr>\n" +
                    "\t\t<td>" + TimeHelper.toWebWithSeconds(sending.getTime()) + "</td>\n" +
                    "\t\t<td>" + LangsTable.selectTaskByID(sending.getLang()).getName() + "</td>\n" +
                    "\t\t<td><font color=\"");
            Status status = sending.getStatus();
            if (status == Status.OK) pw.print("green");
            else pw.print("red");
            pw.print("\">" + sending.getStatus() + "</td>\n" +
                    "\t\t<td>" + sending.getTest() + "</td>\n" +
                    "\t\t<td>" + sending.getCode().replace("\n", "<br>\n") + "</td>\n" +
                    "\t</tr>\n");
        }
    }
}

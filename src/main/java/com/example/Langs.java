package com.example;

import com.example.database.rows.Lang;
import com.example.database.tables.LangsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/langs")
public class Langs extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int id = Integer.parseInt(request.getParameter("l_id"));
        String lName = request.getParameter("l_name");
        String lEnd1 = request.getParameter("l_end1");
        String lEnd2 = request.getParameter("l_end2");
        String lCompile = request.getParameter("l_compile");
        String lExecute = request.getParameter("l_execute");
        int lFreeTime = Integer.parseInt(request.getParameter("l_freeTime"));
        int lFreeMemory = Integer.parseInt(request.getParameter("l_freeMemory"));
        int lMinTime = Integer.parseInt(request.getParameter("l_minTime"));
        int lMinMemory = Integer.parseInt(request.getParameter("l_minMemory"));
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.write(status);
        if (status.equals("Ok")){
            if (id == -1) LangsTable.add(new Lang(lName, lEnd1, lEnd2, lCompile, lExecute, lFreeTime, lFreeMemory, lMinTime, lMinMemory));
            else LangsTable.update(new Lang(id, lName, lEnd1, lEnd2, lCompile, lExecute, lFreeTime, lFreeMemory, lMinTime, lMinMemory));
        }
    }
}
package com.example;

import com.example.database.rows.Constant;
import com.example.database.tables.ConstantsTable;
import com.example.runner.RunQuery;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/constants")
public class Constants extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String cName = request.getParameter("c_name");
        String cValue = request.getParameter("c_value");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.write(status);
        if (status.equals("Ok")){
            ConstantsTable.updateConstant(new Constant(cName, cValue));
            if (cName.equals(Admin.NAME)) Admin.setName(cValue);
            if (cName.equals(Admin.SURNAME)) Admin.setSurname(cValue);
            if (cName.equals(RunQuery.COMPILE_TIME)) RunQuery.setCompileLimit(Long.parseLong(cValue));
        }
    }
}
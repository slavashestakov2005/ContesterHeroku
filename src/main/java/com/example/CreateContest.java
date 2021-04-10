package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create_contest")
public class CreateContest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        if (status.equals("Fail")) pw.print(status);
        else generatePage(pw);
    }

    private void generatePage(PrintWriter pw) {
        pw.print("<center><h3>Название:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_small\" id=\"contest_name\"></textarea>\n");
        pw.print("<center><h3>Условие:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_large\" id=\"contest_description\"></textarea>\n");
        pw.print("<center><h3>Время старта:</h3></center>\n");
        pw.print("<input type=\"datetime-local\" id=\"start_datetime\" " + TimeHelper.getDateTimeAttributes() + ">\n");
        pw.print("<center><h3>Время окончания:</h3></center>\n");
        pw.print("<input type=\"datetime-local\" id=\"finish_datetime\" " + TimeHelper.getDateTimeAttributes() + ">\n");
        pw.print("<center><h3>Пароль:</h3></center>\n");
        pw.print("<input type=\"text\" id=\"password\">\n");
        pw.print("<center>\n" +
                "   <button onclick=\"AddContest(document);\">Создать</button>\n" +
                "</center>\n" +
                "<div id=\"down2\"></div>");
    }
}

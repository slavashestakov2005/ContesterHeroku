package servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku".getBytes());
        out.write(new File("").getAbsoluteFile().toString().getBytes());
        FileWriter writer = new FileWriter(new File("/src/main/webapp/file.html"));
        writer.write("<!DOCTYPE HTML>\n<html>\n<head><title>Файлик</title></head><body>Этот текст написала Java!</body></html>");
        writer.flush();
        writer.close();
        out.flush();
        out.close();
    }
    
}

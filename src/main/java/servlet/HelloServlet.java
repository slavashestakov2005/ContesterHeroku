package servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

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
        out.write("hello heroku<br>".getBytes());
        out.write(new File("").getAbsoluteFile().toString().getBytes());
        out.flush();
        out.close();

        String path = Paths.get("").toAbsolutePath().toString() + "/src/main/webapp/file.html";
        FileWriter writer = new FileWriter(new File(path));
        writer.write("<!DOCTYPE HTML><html><head>Страничка</head><body>Текстик</body></html>");
        writer.flush();
        writer.close();
    }
}

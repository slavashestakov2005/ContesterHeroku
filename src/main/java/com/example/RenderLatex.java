package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/render")
public class RenderLatex extends HttpServlet {
    static private final String head = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<link rel=\"shortcut icon\" href=\"Images/image.ico\" type=\"image/x-icon\">\n" +
            "\t\t<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js\"></script>\n" +
            "\t\t<script src=\"jquery.jslatex.js\"></script>\n" +
            "\t\t<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n" +
            "\t\t<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n" +
            "\t\t<title>Рендеринг LaTeX</title>\n" +
            "\t</head>\n" +
            "\t<body>";
    static private final String tail = "\t</body>\n" +
            "<html>";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String text = request.getParameter("text");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        pw.write(status);
        if (status.equals("Ok")) {
            text = text.replaceAll("\n", " <br>\n");
            PrintWriter writer = new PrintWriter(Root.webDirectory + "\\latex.html", "UTF-8");
            writer.println(head);
            writer.println(text);
            writer.println(tail);
            writer.close();
        }
    }
}

package com.example.generator;

import com.example.Root;
import java.io.*;

public class ContesterPageGenerator {
    public static void generate() throws IOException {
        String pageName = Root.webDirectory + "\\main.html";
        StringBuilder text = new StringBuilder();
        text.append(part1);
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pageName), "UTF-8"));
        out.write(text.toString());
        out.close();
    }

    private static String part1 = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
            "\t\t pageEncoding=\"UTF-8\"%>\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "\n" +
            "<%\n" +
            "\ttry{\n" +
            "\t\tCookie name = new Cookie(\"name\", request.getParameter(\"name\").trim());\n" +
            "\t\tCookie surname = new Cookie(\"surname\", request.getParameter(\"surname\").trim());\n" +
            "\t\tname.setMaxAge(60*60*10);\n" +
            "\t\tsurname.setMaxAge(60*60*10);\n" +
            "\t\t// Add both the cookies in the response header.\n" +
            "\t\tresponse.addCookie(name);\n" +
            "\t\tresponse.addCookie(surname);\n" +
            "\t}catch(Exception e){}\n" +
            "%>\n" +
            "\n" +
            "<html lang=\"ru\">\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<link rel=\"shortcut icon\" href=\"Images/image.ico\" type=\"image/x-icon\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"CSS/users.css\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"CSS/admin.css\">\n" +
            "\t\t<script src=\"JS/users.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script src=\"JS/admin.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n" +
            "\t\t<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n" +
            "\t\t<script> var lang_c = -1; </script>\n" +
            "\t\t<title>Контестер</title>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<div id=\"header\">\n" +
            "\t\t\t<div id=\"nav1\"><center><button id=\"image\" onclick=\"CreateContest(document)\"><img src=\"Images/add.png\"></button></center></div>\n" +
            "\t\t\t<div id=\"nav2\">\n" +
            "\t\t\t\t<p align=\"right\">\n" +
            "\t\t\t\t\t<script>printNameSurname(document);</script>\n" +
            "\t\t\t\t\t<br/>\n" +
            "\t\t\t\t\t<a href=\"index.html\">Выйти</a>\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"nav3\"><center><h1><a href=\"main.html\">Контестер</a></h1></center></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute; \">Список контестов</iframe>\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>Контестер</h2></center>\n" +
            "\t\t\t\t<p>Контестер — web приложение для создания контестов по информатике.</p>\n" +
            "\t\t\t\t<p>Автор: Шестаков Вячеслав, ученик Инженерной Школы гимназии \"Униврс №1\".</p>\n" +
            "\t\t\t\t<p>Руководитель: Вахитова Екатерина Юрьевна, учитель гимназии \"Униврс №1\".</p>\n" +
            "\t\t\t\t<p>Контестер будет применяться в гимназии \"Униврс №1\" на спецкурсе по олимпиадной информатике.</p>\n" +
            "\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

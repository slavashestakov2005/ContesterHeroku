package com.example.generator;

import com.example.Root;
import com.example.database.rows.Contest;
import com.example.database.rows.Task;

import java.io.*;

public class UserPageGenerator {
    public static void generate() throws IOException {
        String pageName = Root.webDirectory + "\\User\\start.html";
        StringBuilder text = new StringBuilder();
        text.append(part1).append("User").append(part2).append("User").append(part3).append("User").append(part4);
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pageName), "UTF-8"));
        out.write(text.toString());
        out.close();
    }

    private static String part1 = "\n" +
            "<!DOCTYPE HTML>\n" +
            "<html lang=\"ru\">\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<link rel=\"shortcut icon\" href=\"../Images/image.ico\" type=\"image/x-icon\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"../CSS/users.css\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"../CSS/admin.css\">\n" +
            "\t\t<script src=\"../JS/users.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script src=\"../JS/admin.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n" +
            "\t\t<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n" +
            "\t\t<script> var lang_c = -1; </script>\n" +
            "\t\t<title>";
    private static String part2 = "</title>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<div id=\"header\">\n" +
            "\t\t\t<div id=\"nav1\"><center><button id=\"image\" onclick=\"CreateContest(document)\"><img src=\"../Images/add.png\"></button></center></div>\n" +
            "\t\t\t<div id=\"nav2\">\n" +
            "\t\t\t\t<p align=\"right\">\n" +
            "\t\t\t\t\t<script>printNameSurname(document);</script>\n" +
            "\t\t\t\t\t<br/>\n" +
            "\t\t\t\t\t<a href=\"edit.html\">Редактировать</a>\n" +
            "\t\t\t\t\t<a href=\"../index.html\">Выйти</a>\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"nav3\"><center><h1><a href=\"start.html\">";
    private static String part3 = "</a></h1></center></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute; \">Список контестов</iframe>\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>";
    private static String part4 = "</h2></center>\n" +
            "\t\t\t\t<p>Описание о Васе.</p>\n" +
            "\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

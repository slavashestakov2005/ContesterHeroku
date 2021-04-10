package com.example.generator;

import com.example.Root;
import com.example.database.rows.Contest;
import com.example.database.rows.Task;

import java.io.*;

public class SolutionPageGenerator {
    public static void generate(Contest contest, Task task) throws IOException {
        String pageName = Root.webDirectory + "\\User\\" + contest.getId() + "\\" + task.getId() + "_solution.jsp";
        StringBuilder text = new StringBuilder();
        text.append(part1).append(task.getId())
                .append(part2).append(contest.getId())
                .append(part3).append(contest.getName())
                .append(part4).append(contest.getName())
                .append(part5).append(task.getName())
                .append(part6).append(Generator.toHTML(task.getSolution(), 4))
                .append(part7).append(task.getId())
                .append(part8);
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pageName), "UTF-8"));
        out.write(text.toString());
        out.close();
    }

    private static String part1 = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
            "\t\tpageEncoding=\"UTF-8\"%>\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "\n" +
            "<html lang=\"ru\">\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<link rel=\"shortcut icon\" href=\"../../Images/image.ico\" type=\"image/x-icon\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"../../CSS/users.css\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"../../CSS/admin.css\">\n" +
            "\t\t<script src=\"../../JS/users.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script src=\"../../JS/admin.js\" type=\"text/javascript\"> </script>\n" +
            "\t\t<script>\n" +
            "\t\t\tvar cnt = -1;\n" +
            "\t\t\tvar page_type = \"solution\";\n" +
            "\t\t\tvar page_number = ";
    private static String part2 = ";\n\t\t\tvar page_contest = ";
    private static String part3 = ";\n" +
            "\t\t\tCheck(document, page_contest);\n" +
            "\t\t</script>\n" +
            "\t\t<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n" +
            "\t\t<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n" +
            "\t\t<title>";
    private static String part4 = "</title>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<div id=\"header\">\n" +
            "\t\t\t<div id=\"nav1\"><center><button id=\"image\" onclick=\"Edit(document, page_type, page_number); \"><img src=\"../../Images/edit.png\"></button></center></div>\n" +
            "\t\t\t<div id=\"nav2\">\n" +
            "\t\t\t\t<p align=\"right\">\n" +
            "\t\t\t\t\t<script>printNameSurname(document);</script>\n" +
            "\t\t\t\t\t<br/>\n" +
            "\t\t\t\t\t<a href=\"edit.html\">Редактировать</a>\n" +
            "\t\t\t\t\t<a href=\"../../index.html\">Выйти</a>\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"nav3\"><center><h1><a href=\"contest.jsp\">";
    private static String part5 = "</a></h1></center></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute;\">Список задач</iframe>\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>";
    private static String part6 = "</h2></center>\n" +
            "\t\t\t\t<h3>Решение:</h3>\n";
    private static String part7 = "\t\t\t\t<button onclick=\"document.location.href='";
    private static String part8 = ".jsp'\">Вернуться</button>\n" +
            "\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

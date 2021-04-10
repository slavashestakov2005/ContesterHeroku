package com.example.generator;

import com.example.Root;
import com.example.TimeHelper;
import com.example.database.rows.Contest;

import java.io.*;

public class StartPageGenerator {
    public static void generate(Contest contest) throws IOException {
        String pageName = Root.webDirectory + "\\User\\" + contest.getId() + ".html";
        StringBuilder text = new StringBuilder();
        text.append(part1).append(contest.getName())
                .append(part2).append(contest.getName())
                .append(part3);
        String now = contest.getAbout();
        if (now != null && now.length() > 0) text.append(part4).append(Generator.toHTML(now, 4));
        text.append(part5).append(TimeHelper.toWeb(contest.getStart()))
                .append(part6).append(TimeHelper.toWeb(contest.getFinish()))
                .append(part7).append(TimeHelper.toDuration(contest.getFinish() - contest.getStart()))
                .append(part8).append(contest.getId())
                .append(part9).append(contest.getId())
                .append(part10);
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
            "\t\t\t<div id=\"nav3\"><center><h1><a href=\"start.html\">User</a></h1></center></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute; \">Список контестов</iframe>\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>";
    private static String part3 = "</h2></center>\n";
    private static String part4 = "\t\t\t\t<h3>Описание:</h3>\n";
    private static String part5 = "\t\t\t\t<h3>Время старта:</h3>\n\t\t\t\t<p>";
    private static String part6 = "</p>\n\t\t\t\t<h3>Время окончания:</h3>\n\t\t\t\t<p>";
    private static String part7 = "</p>\n\t\t\t\t<h3>Продолжительность:</h3>\n\t\t\t\t<p>";
    private static String part8 = "</p>\n\t\t\t\t<h3>Результаты:</h3>\n" +
            "\t\t\t\t<p><a href=\"#\" onclick=\"viewResults(";
    private static String part9 = ", 0);\">Результаты</a></p>\n\t\t\t\t<center>\n" +
            "\t\t\t\t\t<form action=\"";
    private static String part10 = "/contest.jsp\" method=\"GET\">\n" +
            "\t\t\t\t\t\t<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Введите пароль для входа\">\n" +
            "\t\t\t\t\t\t<input type=\"submit\" value=\"Решать\" onclick=\"return Start(this);\" />\n" +
            "\t\t\t\t\t</form>\n" +
            "\t\t\t\t</center>\n" +
            "\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

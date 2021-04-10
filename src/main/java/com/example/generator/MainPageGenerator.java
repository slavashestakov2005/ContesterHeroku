package com.example.generator;

import com.example.Root;
import com.example.TimeHelper;
import com.example.database.rows.Contest;

import java.io.*;

public class MainPageGenerator {
    public static void generate(Contest contest) throws IOException {
        String pageName = Root.webDirectory + "\\User\\" + contest.getId() + "\\contest.jsp";
        StringBuilder text = new StringBuilder();
        text.append(part1).append(contest.getId())
                .append(part2).append(contest.getId())
                .append(part3).append(contest.getName())
                .append(part4).append(contest.getName())
                .append(part5).append(contest.getName())
                .append(part6);
        String now = contest.getAbout();
        if (now != null && now.length() > 0) text.append(part7).append(Generator.toHTML(now, 4));
        text.append(part8).append(TimeHelper.toWeb(contest.getStart()))
                .append(part9).append(TimeHelper.toWeb(contest.getFinish()))
                .append(part10).append(TimeHelper.toDuration(contest.getFinish() - contest.getStart()))
                .append(part11).append(contest.getId())
                .append(part12);
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
            "\t\tString pwd = request.getParameter(\"password\");\n" +
            "\t\tif (pwd != null) {\n" +
            "\t\t\tCookie password = new Cookie(\"password\", pwd);\n" +
            "\t\t\tpassword.setMaxAge(60 * 60 * 10);\n" +
            "\t\t\t// Add both the cookies in the response header.\n" +
            "\t\t\tresponse.addCookie(password);\n" +
            "\t\t}\n" +
            "\t}catch(Exception e){}\n" +
            "%>\n" +
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
            "\t\t\tvar page_type = \"contest\";\n" +
            "\t\t\tvar page_number = ";
    private static String part2 = ";\n" +
            "\t\t\tvar page_contest = ";
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
            "\t\t\t\t\t<a href=\"edit.html\">Редактировать</a>" +
            "\t\t\t\t\t<a href=\"../../index.html\">Выйти</a>\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div id=\"nav3\"><center><h1><a href=\"contest.jsp\">";
    private static String part5 = "</a></h1></center></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute;\">Список задач</iframe>\"\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>";
    private static String part6 = "</h2></center>\n";
    private static String part7 = "\t\t\t\t<h3>Описание:</h3>\n";
    private static String part8 = "\t\t\t\t<h3>Время старта:</h3>\n\t\t\t\t<p>";
    private static String part9 = "</p>\n\t\t\t\t<h3>Время окончания:</h3>\n\t\t\t\t<p>";
    private static String part10 = "</p>\n\t\t\t\t<h3>Продолжительность:</h3>\n\t\t\t\t<p>";
    private static String part11 = "</p>\n\t\t\t\t<h3>Результаты:</h3>\n" +
            "\t\t\t\t<p><a href=\"#\" onclick=\"viewResults(";
    private static String part12 = ", 1);\">Результаты</a></p>\n\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

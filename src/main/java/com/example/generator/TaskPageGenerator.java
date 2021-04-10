package com.example.generator;

import com.example.Root;
import com.example.database.rows.Contest;
import com.example.database.rows.Task;
import com.example.database.rows.Test;

import java.io.*;
import java.util.ArrayList;

public class TaskPageGenerator {
    public static void generate(Contest contest, Task task, ArrayList<Test> tests) throws IOException {
        String pageName = Root.webDirectory + "\\User\\" + contest.getId() + "\\" + task.getId() + ".jsp";
        StringBuilder text = new StringBuilder();
        int timeLimit = task.getTimeLimit();
        text.append(part1).append(task.getId())
                .append(part2).append(contest.getId())
                .append(part3).append(contest.getName())
                .append(part4).append(contest.getName())
                .append(part5).append(task.getName())
                .append(part6);
        if (timeLimit % 1000 == 0) text.append(timeLimit / 1000);
        else text.append(timeLimit / 1000.0);
        text.append(part7).append(task.getMemoryLimit())
                .append(part8);
        String now = task.getAbout();
        if (now != null && now.length() > 0) text.append(part9).append(Generator.toHTML(now, 4));
        now = task.getInput();
        if (now != null && now.length() > 0) text.append(part10).append(Generator.toHTML(now, 4));
        now = task.getOutput();
        if (now != null && now.length() > 0) text.append(part11).append(Generator.toHTML(now, 4));
        if (tests != null && tests.size() > 0) {
            text.append(part12);
            for (Test test : tests) {
                text.append("\t\t\t\t\t<tr>\n").append("\t\t\t\t\t\t<td>\n");
                text.append(Generator.toHTML(test.getInput(), 7));
                text.append("\t\t\t\t\t\t</td>\n").append("\t\t\t\t\t\t<td>\n");
                text.append(Generator.toHTML(test.getOutput(), 7));
                text.append("\t\t\t\t\t\t</td>\n").append("\t\t\t\t\t</tr>\n");
            }
            text.append(part13);
        }
        text.append(part14).append(contest.getId()).append(part15).append(task.getId()).append(part16);
        now = task.getSolution();
        if (now != null && now.length() > 0) text.append(part17).append(task.getId()).append(part18);
        text.append(part19);
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
            "\t\t\tvar page_type = \"task\";\n" +
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
            "\t\t\t<iframe src=\"sidebar.html\" width=\"150px\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" style=\"position: absolute;\">Список задач</iframe>\"\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<center><h2>";
    private static String part6 = "</h2></center>\n" +
            "\t\t\t\t<center>(Время: ";
    private static String part7 = " сек. Память: ";
    private static String part8 = " Мб)</center>\n";
    private static String part9 = "\t\t\t\t<h3>Условие:</h3>\n";
    private static String part10 = "\t\t\t\t<h3>Входные данные:</h3>\n";
    private static String part11 = "\t\t\t\t<h3>Выходные данные:</h3>\n";
    private static String part12 = "\t\t\t\t<h3>Примеры:</h3>\n" +
            "\t\t\t\t<table border=\"1\" width=\"95%\">\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<td width=\"50%\"><center>Input</center></td>\n" +
            "\t\t\t\t\t\t<td width=\"50%\"><center>Output</center></td>\n" +
            "\t\t\t\t\t</tr>\n";
    private static String part13 = "\t\t\t\t</table>\n";
    private static String part14 = "\t\t\t\t<div id=\"code\">\n" +
            "\t\t\t\t\t<p>Решение:</p>\n" +
            "\t\t\t\t\t<% out.print(\"<form action=\\\"../run?contest=";
    private static String part15 = "&task=";
    private static String part16 = "&name=\"); %>${cookie['name'].getValue()}<% out.print(\"&surname=\"); %>${cookie['surname'].getValue()}<% out.print(\"\\\" method=\\\"post\\\">\"); %>\n" +
            "\t\t\t\t\t\t<textarea id=\"code_text\" name=\"code\" placeholder=\"Введите код\" oninput=\"textInput(document)\"></textarea>\n" +
            "\t\t\t\t\t\t<br/>\n" +
            "\t\t\t\t\t\t<input id=\"code_file\" type=\"file\" oninput=\"codeInput(document, 'file');\" onchange=\"readFile(document);\"/>\n" +
            "\t\t\t\t\t\t<select name=\"lang\">\n" +
            "\t\t\t\t\t\t\t<option value=\"cpp\">C++</option>\n" +
            "\t\t\t\t\t\t\t<option value=\"py\">Python</option>\n" +
            "\t\t\t\t\t\t</select>\n" +
            "\t\t\t\t\t\t<input type=\"submit\" value=\"Отправить\" onclick=\"return Start(this);\" />\n" +
            "\t\t\t\t\t</form>\n" +
            "\t\t\t\t</div>\n";
    private static String part17 = "\t\t\t\t<button onclick=\"document.location.href='";
    private static String part18 = "_solution.jsp'\">Решение</button>\n";
    private static String part19 = "\t\t\t\t<button onclick=\"viewCode(document, page_number)\">Мои посылки</button>" +
            "\t\t\t\t<div id=\"down2\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

package com.example.generator;

import com.example.Root;
import com.example.database.rows.Contest;

import java.io.*;
import java.util.ArrayList;

public class ContestsSidebarGenerator {
    public static void generate(ArrayList<Contest> contests) throws IOException {
        String pageName = Root.webDirectory + "\\User\\sidebar.html";
        StringBuilder text = new StringBuilder();
        text.append(part1);
        for(Contest contest : contests){
            text.append("\t\t\t<h2><a href=\"").append(contest.getId()).append(".html\" target=\"_parent\">").append(contest.getName()).append("</a></h2>\n");
        }
        text.append(part2);
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pageName), "UTF-8"));
        out.write(text.toString());
        out.close();
    }

    private static String part1 = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<link rel=\"shortcut icon\" href=\"../Images/image.ico\" type=\"image/x-icon\">\n" +
            "\t\t<link rel=\"stylesheet\" href=\"../CSS/users.css\">\n" +
            "\t\t<script src=\"../JS/users.js\" type=\"text/javascript\"> </script>" +
            "\t\t<title>Контесты</title>\n" +
            "\t</head>\n" +
            "\t<body height=\"auto\">\n" +
            "\t\t<div id=\"sidebar\">\n";
    private static String part2 = "\t\t\t<br/>\n" +
            "\t\t\t<h2><a href=\"../Info/list.html\" target=\"_parent\">Вернуться</a></h2>\n" +
            "\t\t\t<div id=\"down\"></div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}

package com.example;

import com.example.database.rows.Contest;
import com.example.database.rows.Task;
import com.example.database.tables.ContestsTable;
import com.example.database.tables.ContestsTasksTable;
import com.example.database.tables.SendingsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/result")
public class Result extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        int contestId = Integer.parseInt(request.getParameter("contest"));
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Contest contest = ContestsTable.selectContestByID(contestId);
        pw.print("<h2><center>" + contest.getName() + "</center></h2>\n");
        pw.print("<h3>Результаты:</h3>\n" +
                "<table border=\"1\" width=\"95%\">\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"25%\">Участник</td>");
        ArrayList<Task> tasks = ContestsTasksTable.getTasksForContest(contestId);
        ArrayList<Integer> tasksIds = new ArrayList<>();
        for(int taskIndex = 1; taskIndex <= tasks.size(); ++taskIndex){
            pw.print("\t\t<td width=\"10%\">" + taskIndex + "</td>\n");
            tasksIds.add(tasks.get(taskIndex - 1).getId());
        }
        pw.print("\t\t<td width=\"10%\">Итого</td>\n");
        pw.print("\t</tr>\n");
        HashMap<String, HashMap<Integer, Integer>> map = SendingsTable.selectAllForContest(tasksIds);
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String name : map.keySet()){
            HashMap<Integer, Integer> his = map.get(name);
            StringBuilder sb = new StringBuilder();
            sb.append("\t<tr>\n")
                .append("\t\t<td>").append(name).append("</td>\n");
            int result = 0;
            for(Task task : tasks){
                if (!his.containsKey(task.getId())) sb.append("\t\t<td></td>\n");
                else {
                    int value = his.get(task.getId());
                    if (value < 0) sb.append("\t\t<td><font color=\"red\">").append(value).append("</font></td>\n");
                    else {
                        sb.append("\t\t<td><font color=\"green\">+").append(value).append("</font></td>\n");
                        result += 1;
                    }
                }
            }
            sb.append("\t\t<td>").append(result).append("</td>\n")
                .append("\t</tr\n>");
            hashMap.put(sb.toString(), result);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<String, Integer> now : list){
            pw.print(now.getKey());
        }
        pw.print("</table>\n" +
                "<br>\n" +
                "<h3>Задачи:</h3>\n" +
                "<ol>\n");
        for(Task task : tasks) pw.print("\t<li>" + task.getName() + "</li>\n");
        pw.print("</ol>");
    }
}

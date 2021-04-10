package com.example;

import com.example.database.rows.Task;
import com.example.database.rows.Test;
import com.example.database.tables.TasksTable;
import com.example.database.tables.TestsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/edit_task")
public class EditTask extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int taskId = Integer.parseInt(request.getParameter("task"));
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        if (status.equals("Fail")) pw.print(status);
        else generatePage(pw, taskId);
    }

    private void generatePage(PrintWriter pw, int taskId) {
        Task task = TasksTable.selectTaskByID(taskId);
        pw.print("<center><h3>Название:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_small\" id=\"task_name\">" + task.getName() + "</textarea>\n");
        pw.print("<center><h3>Время (мс):</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_small\" id=\"task_time\">" + task.getTimeLimit() + "</textarea>\n");
        pw.print("<center><h3>Память (Мб):</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_small\" id=\"task_memory\">" + task.getMemoryLimit() + "</textarea>\n");
        pw.print("<center><h3>Условие:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_large\" id=\"task_description\">" + task.getAbout() + "</textarea>\n");
        pw.print("<center><h3>Входные данные:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_middle\" id=\"task_input\">" + task.getInput() + "</textarea>\n");
        pw.print("<center><h3>Выходные данные:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_middle\" id=\"task_output\">" + task.getOutput() + "</textarea>\n");
        pw.print("<center><h3>Решение:</h3></center>\n");
        pw.print("<textarea class=\"tasks_data_large\" id=\"task_solution\">" + task.getSolution() + "</textarea>\n");
        pw.print("<center><h3>Тесты:</h3></center>\n");
        pw.print("<table border=\"1\" width=\"100%\" id=\"task\">\n" +
                "            <tr>\n" +
                "                <td width=\"5%\">№</td>\n" +
                "                <td width=\"5%\">Истиный №</td>\n" +
                "                <td width=\"35%\">Ввод</td>\n" +
                "                <td width=\"35%\">Вывод</td>\n" +
                "                <td width=\"5%\">Пример</td>\n" +
                "                <td width=\"5%\">Открытый</td>\n" +
                "                <td width=\"5%\">Изменено</td>\n" +
                "                <td width=\"5%\">Удалить</td>\n" +
                "            </tr>\n");
        ArrayList<Test> tests = TestsTable.getTestsForTask(taskId);
        for(int i = 0; i < tests.size(); ++i){
            pw.print("<tr id=\"" + (i + 1) + "\">\n" +
                     "   <td>" + (i + 1) + "</td>\n" +
                     "   <td><p id=\"index" + (i + 1) + "\">" + tests.get(i).getId() + "</p></td>\n" +
                     "   <td><textarea id=\"input" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + tests.get(i).getInput() + "</textarea></td>\n" +
                     "   <td><textarea id=\"output" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + tests.get(i).getOutput() + "</textarea></td>\n" +
                     "   <td><input id=\"example" + (i + 1) + "\" type=\"checkbox\" onchange=\"Change(document, " + (i + 1) + ");\"" + (tests.get(i).isExample() ? " checked" : "") + "></td>\n" +
                     "   <td><input id=\"public"  + (i + 1) + "\" type=\"checkbox\" onchange=\"Change(document, " + (i + 1) + ");\"" + (tests.get(i).isPublic() ? " checked" : "") + "></td>\n" +
                     "   <td><button id=\"btn" + (i + 1) + "\" disabled>Изменено</button></td>\n" +
                     "   <td><button onclick=\"DeleteTest(document, " + (i + 1) + ")\">Удалить</button></td>\n" +
                     "</tr>\n");
        }
        pw.print("</table>\n" +
                 "<br/>\n" +
                 "<center>\n" +
                 "   <button onclick=\"if(cnt === -1) cnt = " + tests.size() + "; Save(document, cnt, 'task', " + task.getId() + ");\">Сохранить всё</button>\n" +
                 "   <button onclick=\"if(cnt === -1) cnt = " + tests.size() + "; ++cnt; NewRow(document, cnt);\">Новый тест</button>\n" +
                 "</center>\n" +
                "<div id=\"down2\"></div>");
    }
}
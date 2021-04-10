package com.example;

import com.example.database.rows.Constant;
import com.example.database.rows.Lang;
import com.example.database.tables.ConstantsTable;
import com.example.database.tables.LangsTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/edit_settings")
public class EditSettings extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** get Parameters **/
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        /** work with they **/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        final String status;
        if (Admin.checkUser(name, surname)) status = "Ok";
        else status = "Fail";
        if (status.equals("Fail")) pw.print(status);
        else generatePage(pw);
    }

    private void generatePage(PrintWriter pw) {
        pw.print("<center><h3>Правила записи:</h3></center>\n" +
                "Для записи команд запуска и компиляции используется синтаксис java.text.MessageFormat.format, где:<br>\n" +
                "{0} — обозначает имя программы (файл для запуска).<br>\n" +
                "{1} — обозначает имя файла с кодом (файл для компиляции).<br>\n" +
                "<center><h3>Доступные языки:</h3></center>\n" +
                "<table border=\"1\" width=\"100%\" id=\"langs\">\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"3%\"><center>№</center></td>\n" +
                "\t\t<td width=\"7%\"><center>Название</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Исходное расширение</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Конечное расширение</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Свободное время</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Свободная память</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Минимальное время</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Минимальная память</center></td>\n" +
                "\t\t<td width=\"35%\"><center>Компиляция</center></td>\n" +
                "\t\t<td width=\"15%\"><center>Запуск</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Изменено</center></td>\n" +
                "\t\t<td width=\"5%\"><center>Удалить</center></td>\n" +
                "\t</tr>\n");
        /** get all langs */
        ArrayList<Lang> langs = LangsTable.getAll();
        for(int i = 0; i < langs.size(); ++i){
            pw.print("\t<tr id=\"" + (i + 1) + "\">\n" +
                    "\t\t<td><p id=\"index" + (i + 1) + "\">" + langs.get(i).getId() + "</p></td>\n" +
                    "\t\t<td><textarea id=\"name" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getName() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"end1" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getEnd1() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"end2" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getEnd2() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"free_time" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getFreeTime() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"free_memory" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getFreeMemory() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"min_time" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getMinTime() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"min_memory" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getMinMemory() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"compile" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getCompileCommand() + "</textarea></td>\n" +
                    "\t\t<td><textarea id=\"execute" + (i + 1) + "\" class=\"input_output\" oninput=\"Change(document, " + (i + 1) + ");\">" + langs.get(i).getExecuteCommand() + "</textarea></td>\n" +
                    "\t\t<td><button id=\"btn" + (i + 1) + "\" disabled>Изменено</button></td>\n" +
                    "\t\t<td><button onclick=\"DeleteLang(document, " + (i + 1) + ")\">Удалить</button></td>\n" +
                    "\t</tr>\n");
        }
        pw.print("</table>\n" +
                "<br>\n" +
                "<center>\n" +
                "\t<button onclick=\"if(lang_c === -1) lang_c = " + langs.size() + "; SaveLangs(document, lang_c);\">Сохранить языки</button>\n" +
                "\t<button onclick=\"if(lang_c === -1) lang_c = " + langs.size() + "; ++lang_c; NewRowLang(document, lang_c);\">Новый язык</button>\n" +
                "</center>\n" +
                "<br>\n" +
                "<center><h3>Константы:</h3></center>\n" +
                "<table border=\"1\" width=\"100%\" id=\"constants\">\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"45%\"><center>Имя</center></td>\n" +
                "\t\t<td width=\"45%\"><center>Значение</center></td>\n" +
                "\t\t<td width=\"10%\"><center>Изменено</center></td>\n" +
                "\t</tr>\n");
        ArrayList<Constant> constants = ConstantsTable.getAllConstants();
        for(int i = 0; i < constants.size(); ++i){
            pw.print("\t<tr>\n" +
                    "\t\t<td><p id=\"constant_name" + (i + 1) + "\">" + constants.get(i).getName() + "</p></td>\n" +
                    "\t\t<td><textarea id=\"constant_value" + (i + 1) + "\" class=\"input_output\" oninput=\"ChangeConstant(document, " + (i + 1) + ");\">" + constants.get(i).getValue() + "</textarea></td>\n" +
                    "\t\t<td><button id=\"constant_edit" + (i + 1) + "\" disabled>Изменено</button></td>\n" +
                    "\t</tr>\n");
        }
        pw.print("</table>\n" +
                "<br>\n" +
                "<center>\n" +
                "\t<button onclick=\"SaveConstants(document, " + constants.size() + ");\">Сохранить константы</button>\n" +
                "</center>\n" +
                "<br>\n" +
                "<br/>\n" +
                "<center>\n" +
                "\t<button onclick=\"window.open('../latex_render.html', '_blank')\">Просмотр LaTeX</button>\n" +
                "\t<button onclick=\"window.open('../res_loader.html', '_blank')\">Загрузка файлов</button>\n" +
                "</center>\n" +
                "<div id=\"down2\"></div>");
    }
}

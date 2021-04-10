package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.ContestTask;
import com.example.database.rows.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContestsTasksTable {
    private static final String table = "contests_tasks";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("CONTEST", 1, "id_contest");   // int       NOT NULL    PK
        columns.add("TASK", 2, "id_task");         // int       NOT NULL    PK
    }

    public static ArrayList<Task> getTasksForContest(int contestId) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT " + columns.getName("TASK")+ " FROM " + table + " WHERE " + columns.getName("CONTEST") + " = " + contestId);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int taskId = resultSet.getInt(1);
                    DataBaseHelper.push();
                    tasks.add(TasksTable.selectTaskByID(taskId));
                    DataBaseHelper.pop();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void delete(ContestTask contestTask){
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + contestTask.deleteString());
    }

    public static void add(ContestTask contestTask) {
        DataBaseHelper.execute("INSERT INTO " + table + " VALUES " + contestTask.insertString());
    }
}

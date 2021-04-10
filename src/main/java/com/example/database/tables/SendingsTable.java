package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.Sending;
import com.example.runner.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SendingsTable {
    private static final String table = "sendings";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("USER", 1, "user_id");      // text     NOT NULL
        columns.add("TASK", 2, "task_id");      // int      NOT NULL
        columns.add("STATUS", 3, "status");     // int      NOT NULL
        columns.add("CODE", 4, "code");         // text
        columns.add("LANG", 5, "lang_id");      // int      NOT NULL
        columns.add("TIME", 6, "time");         // int      NOT NULL
        columns.add("TEST", 7, "bad_test");     // int      NOT NULL
    }

    public static void add(Sending sending) {
        DataBaseHelper.execute("INSERT INTO " + table + " " + sending.insertString());
    }

    public static HashMap<String, HashMap<Integer, Integer>> selectAllForContest(ArrayList<Integer> tasks){
        if (tasks.isEmpty()) return null;
        String task = columns.getName("TASK");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(table).append(" WHERE ").append(task).append(" = ").append(tasks.get(0));
        for(int i = 1; i < tasks.size(); ++i) sql.append(" OR ").append(task).append(" = ").append(tasks.get(i));
        HashMap<String, HashMap<Integer, Integer>> result = new HashMap<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery(sql.toString());
            if (resultSet != null) {
                while (resultSet.next()) {
                    Sending sending = Sending.parseSQL(resultSet);
                    if (!result.containsKey(sending.getUser())) result.put(sending.getUser(), new HashMap<>());
                    if (!result.get(sending.getUser()).containsKey(sending.getTask())) result.get(sending.getUser()).put(sending.getTask(), 0);
                    int now = result.get(sending.getUser()).get(sending.getTask());
                    if (now <= 0) {
                        if (statusToBoolean(sending.getStatus()))
                            result.get(sending.getUser()).put(sending.getTask(), -now);
                        else result.get(sending.getUser()).put(sending.getTask(), now - 1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean statusToBoolean(Status status) {
        return status == Status.OK;
    }

    public static ArrayList<Sending> selectAllForUser(String user) {
        ArrayList<Sending> result = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("USER") + " = '" + DataBaseHelper.toSQL(user) + "'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    result.add(Sending.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

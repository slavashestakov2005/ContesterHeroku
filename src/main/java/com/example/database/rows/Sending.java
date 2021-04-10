package com.example.database.rows;

import com.example.database.DataBaseHelper;
import com.example.database.tables.SendingsTable;
import com.example.runner.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sending {
    private String user;
    private int task, status;
    private String code;
    private int lang;
    private long time;
    private int test;

    private Sending(boolean type, String user, int task, int status, String code, int lang, long time, int test) {
        this.user = user;
        this.task = task;
        this.status = status;
        this.code = code;
        this.lang = lang;
        this.time = time;
        this.test = test;
    }

    public Sending(String user, int task, Status status, String code, int lang, long time, int test) {
        this.user = DataBaseHelper.toSQL(user);
        this.task = task;
        this.status = status.getId();
        this.code = DataBaseHelper.toSQL(code);
        this.lang = lang;
        this.time = time;
        this.test = test;
    }

    public String getUser() {
        return user;
    }

    public int getTask() {
        return task;
    }

    public Status getStatus() {
        return Status.fromInt(status);
    }

    public String getCode() {
        return code;
    }

    public int getLang() {
        return lang;
    }

    public long getTime() {
        return time;
    }

    public int getTest() {
        return test;
    }

    public static Sending parseSQL(ResultSet resultSet) throws SQLException {
        String user = resultSet.getString(SendingsTable.columns.getIndex("USER"));
        int task = resultSet.getInt(SendingsTable.columns.getIndex("TASK"));
        int status = resultSet.getInt(SendingsTable.columns.getIndex("STATUS"));
        String code = resultSet.getString(SendingsTable.columns.getIndex("CODE"));
        int lang = resultSet.getInt(SendingsTable.columns.getIndex("LANG"));
        long time = resultSet.getLong(SendingsTable.columns.getIndex("TIME"));
        int test = resultSet.getInt(SendingsTable.columns.getIndex("TEST"));
        return new Sending(false, user, task, status, code, lang, time, test);
    }

    @Override
    public String toString() {
        return "Sending{" +
                "user='" + user + '\'' +
                ", task=" + task +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", lang=" + lang +
                ", time=" + time +
                ", test=" + test +
                '}';
    }

    public String insertString() {
        return "(" + SendingsTable.columns.getName("USER") + ", " +
                SendingsTable.columns.getName("TASK") + ", " +
                SendingsTable.columns.getName("STATUS") + ", " +
                SendingsTable.columns.getName("CODE") + ", " +
                SendingsTable.columns.getName("LANG") + ", " +
                SendingsTable.columns.getName("TIME") + ", " +
                SendingsTable.columns.getName("TEST") + ") VALUES ('" +
                user + "', " + task + ", " + status + ", '" + code + "', " + lang + ", " + time + ", " + test + ")";
    }
}

package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestsTable {
    private static final String table = "tests";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("ID", 1, "id");             // int      NOT NULL    PK      AI      UNIQUE
        columns.add("TASK", 2, "id_task");      // int      NOT NULL
        columns.add("INPUT", 3, "input");       // text
        columns.add("OUTPUT", 4, "output");     // text
        columns.add("EXAMPLE", 5, "example");   // text     NOT NULL
        columns.add("PUBLIC", 6, "public");     // text     NOT NULL
    }

    public static Test selectTestByID(int testId){
        Test test = null;
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("ID") + " = " + testId);
            if (resultSet != null && resultSet.next()) {
                test = Test.parseSQL(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static ArrayList<Test> getTestsForTask(int taskId){
        ArrayList<Test> tests = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("TASK") + " = " + taskId);
            if (resultSet != null) {
                while (resultSet.next()) {
                    tests.add(Test.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static ArrayList<Test> getExampleTestsForTask(int taskId) {
        ArrayList<Test> tests = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " +
                    columns.getName("TASK") + " = " + taskId + " AND " + columns.getName("EXAMPLE") + " = 1");
            if (resultSet != null) {
                while (resultSet.next()) {
                    tests.add(Test.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static void add(Test test) {
        DataBaseHelper.execute("INSERT INTO " + table + test.insertString());
    }

    public static void update(Test test){
        DataBaseHelper.execute("UPDATE " + table + " SET " + test.updateString() + " WHERE " + columns.getName("ID") + " = " + test.getId());
    }

    public static void delete(int testId) {
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("ID") + " = " + testId);
    }

    public static void deleteForTask(int taskId) {
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("TASK") + " = " + taskId);
    }
}

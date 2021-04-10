package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.Contest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContestsTable {
    private static final String table = "contests";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("ID", 1, "id");             // int      NOT NULL    PK      AI      UNIQUE
        columns.add("NAME", 2, "name");         // text     NOT NULL
        columns.add("ABOUT", 3, "description"); // text
        columns.add("START", 4, "start");       // int      NOT NULL
        columns.add("FINISH", 5, "finish");     // int      NOT NULL
        columns.add("PASSWORD", 6, "password"); // text     NOT NULL
    }

    public static ArrayList<Contest> getAll() {
        ArrayList<Contest> contests = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table);
            if (resultSet != null) {
                while (resultSet.next()) {
                    contests.add(Contest.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contests;
    }

    public static Contest selectContestByID(int contestId){
        Contest contest = null;
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("ID") + " = " + contestId);
            if (resultSet != null && resultSet.next()) {
                contest = Contest.parseSQL(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contest;
    }

    public static void updateContestByID(Contest contest){
        DataBaseHelper.execute("UPDATE " + table + " SET " + contest.updateString() + " WHERE " + columns.getName("ID") + " = " + contest.getId());
    }

    public static void delete(int contestId){
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("ID") + " = " + contestId);
    }

    public static int add(Contest contest) {
        try {
            DataBaseHelper.execute("INSERT INTO " + table + contest.insertString());
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT last_insert_rowid()");
            if (resultSet != null && resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

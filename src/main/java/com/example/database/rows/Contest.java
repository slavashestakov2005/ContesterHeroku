package com.example.database.rows;

import com.example.database.DataBaseHelper;
import com.example.database.tables.ContestsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Contest {
    private int id;
    private String name, about;
    private long start, finish;
    private String password;

    private Contest(boolean type, int id, String name, String about, long start, long finish, String password) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.start = start;
        this.finish = finish;
        this.password = password;
    }

    public Contest(int id, String name, String about, long start, long finish, String password) {
        this.id = id;
        this.name = DataBaseHelper.toSQL(name);
        this.about = DataBaseHelper.toSQL(about);
        this.start = start;
        this.finish = finish;
        this.password = DataBaseHelper.toSQL(password);
    }

    public Contest(String name, String about, long start, long finish, String password) {
        this.name = DataBaseHelper.toSQL(name);
        this.about = DataBaseHelper.toSQL(about);
        this.start = start;
        this.finish = finish;
        this.password = DataBaseHelper.toSQL(password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public long getStart() {
        return start;
    }

    public long getFinish() {
        return finish;
    }

    public String getPassword() {
        return password;
    }

    public static Contest parseSQL(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ContestsTable.columns.getIndex("ID"));
        String name = resultSet.getString(ContestsTable.columns.getIndex("NAME"));
        String about = resultSet.getString(ContestsTable.columns.getIndex("ABOUT"));
        long start = resultSet.getLong(ContestsTable.columns.getIndex("START"));
        long finish = resultSet.getLong(ContestsTable.columns.getIndex("FINISH"));
        String password = resultSet.getString(ContestsTable.columns.getIndex("PASSWORD"));
        return new Contest(false, id, name, about, start, finish, password);
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String updateString(){
        return ContestsTable.columns.getName("NAME") + " = '" + name + "', " +
                ContestsTable.columns.getName("ABOUT") + " = '" + about + "', " +
                ContestsTable.columns.getName("START") + " = " + start + ", " +
                ContestsTable.columns.getName("FINISH") + " = " + finish + ", " +
                ContestsTable.columns.getName("PASSWORD") + " = '" + password + "'";
    }

    public String insertString() {
        return "(" + ContestsTable.columns.getName("NAME") + ", " +
                ContestsTable.columns.getName("ABOUT") + ", " +
                ContestsTable.columns.getName("START") + ", " +
                ContestsTable.columns.getName("FINISH") + ", " +
                ContestsTable.columns.getName("PASSWORD") + ") VALUES ('" +
                name + "', '" +  about + "', " + start + ", " + finish + ", '" + password + "')";
    }
}

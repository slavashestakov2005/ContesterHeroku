package com.example.database.rows;

import com.example.database.DataBaseHelper;
import com.example.database.tables.LangsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lang {
    private int id;
    private String name, end1, end2, compileCommand, executeCommand;
    private int freeTime, freeMemory, minTime, minMemory;

    private Lang(boolean type, int id, String name, String end1, String end2, String compileCommand, String executeCommand, int freeTime, int freeMemory, int minTime, int minMemory) {
        this.id = id;
        this.name = name;
        this.end1 = end1;
        this.end2 = end2;
        this.compileCommand = compileCommand;
        this.executeCommand = executeCommand;
        this.freeTime = freeTime;
        this.freeMemory = freeMemory;
        this.minTime = minTime;
        this.minMemory = minMemory;
    }

    public Lang(int id, String name, String end1, String end2, String compileCommand, String executeCommand, int freeTime, int freeMemory, int minTime, int minMemory) {
        this.id = id;
        this.name = DataBaseHelper.toSQL(name);
        this.end1 = DataBaseHelper.toSQL(end1);
        this.end2 = DataBaseHelper.toSQL(end2);
        this.compileCommand = DataBaseHelper.toSQL(compileCommand);
        this.executeCommand = DataBaseHelper.toSQL(executeCommand);
        this.freeTime = freeTime;
        this.freeMemory = freeMemory;
        this.minTime = minTime;
        this.minMemory = minMemory;
    }

    public Lang(String name, String end1, String end2, String compileCommand, String executeCommand, int freeTime, int freeMemory, int minTime, int minMemory) {
        this.name = DataBaseHelper.toSQL(name);
        this.end1 = DataBaseHelper.toSQL(end1);
        this.end2 = DataBaseHelper.toSQL(end2);
        this.compileCommand = DataBaseHelper.toSQL(compileCommand);
        this.executeCommand = DataBaseHelper.toSQL(executeCommand);
        this.freeTime = freeTime;
        this.freeMemory = freeMemory;
        this.minTime = minTime;
        this.minMemory = minMemory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnd1() {
        return end1;
    }

    public String getEnd2() {
        return end2;
    }

    public String getCompileCommand() {
        return compileCommand;
    }

    public String getExecuteCommand() {
        return executeCommand;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public int getFreeMemory() {
        return freeMemory;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMinMemory() {
        return minMemory;
    }

    public static Lang parseSQL(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(LangsTable.columns.getIndex("ID"));
        String name = resultSet.getString(LangsTable.columns.getIndex("NAME"));
        String end1 = resultSet.getString(LangsTable.columns.getIndex("END1"));
        String end2 = resultSet.getString(LangsTable.columns.getIndex("END2"));
        String compile = resultSet.getString(LangsTable.columns.getIndex("COMPILE"));
        String execute = resultSet.getString(LangsTable.columns.getIndex("EXECUTE"));
        int freeTime = resultSet.getInt(LangsTable.columns.getIndex("FREE_TIME"));
        int freeMemory = resultSet.getInt(LangsTable.columns.getIndex("FREE_MEMORY"));
        int minTime = resultSet.getInt(LangsTable.columns.getIndex("MIN_TIME"));
        int minMemory = resultSet.getInt(LangsTable.columns.getIndex("MIN_MEMORY"));
        return new Lang(false, id, name, end1, end2, compile, execute, freeTime, freeMemory, minTime, minMemory);
    }

    @Override
    public String toString() {
        return "Lang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", end1='" + end1 + '\'' +
                ", end2='" + end2 + '\'' +
                ", compileCommand='" + compileCommand + '\'' +
                ", executeCommand='" + executeCommand + '\'' +
                ", freeTime=" + freeTime +
                ", freeMemory=" + freeMemory +
                ", minTime=" + minTime +
                ", minMemory=" + minMemory +
                '}';
    }

    public String insertString() {
        return "(" + LangsTable.columns.getName("NAME") + ", " +
                LangsTable.columns.getName("END1") + ", " +
                LangsTable.columns.getName("END2") + ", " +
                LangsTable.columns.getName("COMPILE") + ", " +
                LangsTable.columns.getName("EXECUTE") + ", " +
                LangsTable.columns.getName("FREE_TIME") + ", " +
                LangsTable.columns.getName("FREE_MEMORY") + ", " +
                LangsTable.columns.getName("MIN_TIME") + ", " +
                LangsTable.columns.getName("MIN_MEMORY") + ") VALUES ('" +
                name + "', '" +  end1 + "', '" + end2 + "', '" +  compileCommand + "', '" + executeCommand + "', " +
                freeTime + ", " + freeMemory + ", " + minTime + ", " + minMemory + ")";
    }

    public String updateString() {
        return LangsTable.columns.getName("NAME") + " = '" + name + "', " +
                LangsTable.columns.getName("END1") + " = '" + end1 + "', " +
                LangsTable.columns.getName("END2") + " = '" + end2 + "', " +
                LangsTable.columns.getName("COMPILE") + " = '" + compileCommand + "', " +
                LangsTable.columns.getName("EXECUTE") + " = '" + executeCommand + "', " +
                LangsTable.columns.getName("FREE_TIME") + " = " + freeTime + ", " +
                LangsTable.columns.getName("FREE_MEMORY") + " = " + freeMemory + ", " +
                LangsTable.columns.getName("MIN_TIME") + " = " + minTime + ", " +
                LangsTable.columns.getName("MIN_MEMORY") + " = " + minMemory;
    }
}

package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.Lang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LangsTable {
    private static final String table = "langs";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("ID", 1, "id");                     // int      NOT NULL    PK      AI      UNIQUE
        columns.add("NAME", 2, "name");                 // text     NOT NULL
        columns.add("END1", 3, "end1");                 // text     NOT NULL                    UNIQUE
        columns.add("END2", 4, "end2");                 // text     NOT NULL
        columns.add("COMPILE", 5, "compile");           // text     NOT NULL
        columns.add("EXECUTE", 6, "execute");           // text     NOT NULL
        columns.add("FREE_TIME", 7, "freeTime");        // int      NOT NULL    (MS)
        columns.add("FREE_MEMORY", 8, "freeMemory");    // int      NOT NULL    (KB)
        columns.add("MIN_TIME", 9, "minTime");          // int      NOT NULL    (MS)
        columns.add("MIN_MEMORY", 10, "minMemory");     // int      NOT NULL    (KB)
    }

    public static ArrayList<Lang> getAll() {
        ArrayList<Lang> langs = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table);
            if (resultSet != null) {
                while (resultSet.next()) {
                    langs.add(Lang.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return langs;
    }

    public static void add(Lang lang) {
        DataBaseHelper.execute("INSERT INTO " + table + lang.insertString());
    }

    public static void update(Lang lang) {
        DataBaseHelper.execute("UPDATE " + table + " SET " + lang.updateString() + " WHERE " + columns.getName("ID") + " = " + lang.getId());
    }

    public static void delete(int testId) {
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("ID") + " = " + testId);
    }

    public static Lang getLang(String end1){
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("END1") + " = '" + end1 + "'");
            if (resultSet != null) return Lang.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Lang selectTaskByID(int langId) {
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + columns.getName("ID") + " = " + langId);
            if (resultSet != null) return Lang.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

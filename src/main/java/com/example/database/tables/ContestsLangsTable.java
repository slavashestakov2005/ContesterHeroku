package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.ContestLang;
import com.example.database.rows.Lang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContestsLangsTable {
    private static final String table = "contests_langs";
    public static final Columns columns;

    static {
        columns = new Columns();
        columns.add("CONTEST", 1, "id_contest");   // int       NOT NULL    PK
        columns.add("LANG", 2, "id_lang");         // int       NOT NULL    PK
    }

    public static ArrayList<Lang> getLangsForContest(int contestId) {
        ArrayList<Lang> langs = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT " + columns.getName("LANG")+ " FROM " + table + " WHERE " + columns.getName("CONTEST") + " = " + contestId);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int langId = resultSet.getInt(1);
                    DataBaseHelper.push();
                    langs.add(LangsTable.selectTaskByID(langId));
                    DataBaseHelper.pop();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return langs;
    }

    public static boolean isExists(ContestLang contestLang){
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " + contestLang.deleteString());
            if (resultSet != null) return !resultSet.isAfterLast();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void delete(ContestLang contestLang){
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + contestLang.deleteString());
    }

    public static void add(ContestLang contestLang) {
        DataBaseHelper.execute("INSERT INTO " + table + " VALUES " + contestLang.insertString());
    }
}

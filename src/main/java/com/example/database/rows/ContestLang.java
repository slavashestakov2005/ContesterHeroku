package com.example.database.rows;

import com.example.database.tables.ContestsLangsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContestLang {
    private int contest, lang;

    public ContestLang(int contest, int lang) {
        this.contest = contest;
        this.lang = lang;
    }

    public int getContest() {
        return contest;
    }

    public int getLang() {
        return lang;
    }

    public static ContestLang parseSQL(ResultSet resultSet) throws SQLException {
        int contest = resultSet.getInt(ContestsLangsTable.columns.getIndex("CONTEST"));
        int lang = resultSet.getInt(ContestsLangsTable.columns.getIndex("LANG"));
        return new ContestLang(contest, lang);
    }

    @Override
    public String toString() {
        return "ContestTask{" +
                "contest=" + contest +
                ", lang=" + lang +
                '}';
    }

    public String deleteString(){
        return ContestsLangsTable.columns.getName("CONTEST") + " = " + contest + " AND " +
                ContestsLangsTable.columns.getName("LANG") + " = " + lang;
    }

    public String insertString() {
        return "(" + contest + ", " + lang + ")";
    }
}

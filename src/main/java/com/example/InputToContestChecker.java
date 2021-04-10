package com.example;

import com.example.database.rows.Contest;
import com.example.database.tables.ContestsTable;

public class InputToContestChecker {
    private static boolean checkPassword(Contest contest, String password){
        return password.equals(contest.getPassword());
    }

    private static boolean checkTime(Contest contest){
        long time = System.currentTimeMillis();
        return contest.getStart() <= time && time <= contest.getFinish();
    }

    public static boolean check(String password, int contestId){
        Contest contest = ContestsTable.selectContestByID(contestId);
        if (contest == null) return false;
        return checkPassword(contest, password) && checkTime(contest);
    }
}

package com.example.generator;

import com.example.Root;
import com.example.database.rows.Contest;
import com.example.database.rows.Task;
import com.example.database.rows.Test;
import com.example.database.tables.ContestsTable;
import com.example.database.tables.ContestsTasksTable;
import com.example.database.tables.TestsTable;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Generator {
    public static void start(int contestId) throws IOException {
        Contest contest = ContestsTable.selectContestByID(contestId);
        ArrayList<Task> tasks = ContestsTasksTable.getTasksForContest(contestId);
        deleteOldFiles(contestId);
        createNewDirectories(contestId, tasks);
        MainPageGenerator.generate(contest);
        for(Task task : tasks){
            TaskPageGenerator.generate(contest, task, TestsTable.getExampleTestsForTask(task.getId()));
            String solution = task.getSolution();
            if (solution != null && solution.length() > 0) SolutionPageGenerator.generate(contest, task);
        }
        StartPageGenerator.generate(contest);
        /***/ContesterPageGenerator.generate();
        TasksSidebarGenerator.generate(contest, tasks);
        ContestsSidebarGenerator.generate(ContestsTable.getAll());
        UserPageGenerator.generate();
    }

    public static void deleteOldFiles(int contestId) throws IOException {
        Path directory = Paths.get(Root.webDirectory + "\\User\\" + contestId);
        if (Files.exists(directory)) Files.walkFileTree(directory, new DeleteVisitor());
    }

    public static void createNewDirectories(int contestId, ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get(Root.webDirectory + "\\User\\" + contestId));
    }

    public static String toHTML(String text, int tabs){
        String lines[] = text.split("[\r\n]+");
        StringBuilder tabBuilder = new StringBuilder();
        for(int i = 0; i < tabs; ++i) tabBuilder.append("\t");
        String tab = tabBuilder.toString();
        StringBuilder builder = new StringBuilder();
        for(String line : lines){
            builder.append(tab).append("<p>").append(line).append("</p>\n");
        }
        return builder.toString();
    }
}

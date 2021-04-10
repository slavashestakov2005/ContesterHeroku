package com.example.database.tables;

import com.example.Root;
import com.example.database.rows.ContestTask;
import com.example.database.rows.Task;
import com.example.generator.ContestsSidebarGenerator;
import com.example.generator.DeleteVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TablesHelper {
    public static void deleteTest(int idTest){
        TestsTable.delete(idTest);
    }

    public static void deleteTask(int idContest, int idTask){
        TasksTable.delete(idTask);
        TestsTable.deleteForTask(idTask);
        ContestsTasksTable.delete(new ContestTask(idContest, idTask));
    }

    public static void deleteContest(int idContest) throws IOException {
        ContestsTable.delete(idContest);
        ArrayList<Task> tasks = ContestsTasksTable.getTasksForContest(idContest);
        for(Task task : tasks) deleteTask(idContest, task.getId());
        Path directory = Paths.get(Root.webDirectory + "\\" + idContest);
        if (Files.exists(directory)) Files.walkFileTree(directory, new DeleteVisitor());
        directory = Paths.get(Root.rootDirectory + "\\Contests\\" + idContest);
        if (Files.exists(directory)) Files.walkFileTree(directory, new DeleteVisitor());
        directory = Paths.get(Root.webDirectory + "\\" + idContest + ".jsp");
        if (Files.exists(directory)) Files.delete(directory);
        ContestsSidebarGenerator.generate(ContestsTable.getAll());
    }
}

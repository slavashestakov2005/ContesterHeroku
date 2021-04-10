package com.example.database.rows;

import com.example.database.DataBaseHelper;
import com.example.database.tables.TestsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    private int id, task;
    private String input, output;
    private boolean isExample, isPublic;

    private Test(boolean type, int id, int task, String input, String output, boolean isExample, boolean isPublic) {
        this.id = id;
        this.task = task;
        this.input = input;
        this.output = output;
        this.isExample = isExample;
        this.isPublic = isPublic;
    }

    public Test(int id, int task, String input, String output, boolean isExample, boolean isPublic) {
        this.id = id;
        this.task = task;
        this.input = DataBaseHelper.toSQL(input);
        this.output = DataBaseHelper.toSQL(output);
        this.isExample = isExample;
        this.isPublic = isPublic;
    }

    public Test(int task, String input, String output, boolean isExample, boolean isPublic) {
        this.task = task;
        this.input = DataBaseHelper.toSQL(input);
        this.output = DataBaseHelper.toSQL(output);
        this.isExample = isExample;
        this.isPublic = isPublic;
    }

    public int getId() {
        return id;
    }

    public int getTask() {
        return task;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public boolean isExample() {
        return isExample;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public static Test parseSQL(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(TestsTable.columns.getIndex("ID"));
        int task = resultSet.getInt(TestsTable.columns.getIndex("TASK"));
        String input = resultSet.getString(TestsTable.columns.getIndex("INPUT"));
        String output = resultSet.getString(TestsTable.columns.getIndex("OUTPUT"));
        boolean isExample = resultSet.getInt(TestsTable.columns.getIndex("EXAMPLE")) == 1;
        boolean isPublic = resultSet.getInt(TestsTable.columns.getIndex("PUBLIC")) == 1;
        return new Test(false, id, task, input, output, isExample, isPublic);
    }

    @Override
    public String toString() {
        return "Test{" +
                "task=" + task +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", isExample=" + isExample +
                ", isPublic=" + isPublic +
                '}';
    }

    public String updateString(){
        return TestsTable.columns.getName("TASK") + " = '" + task + "', " +
                TestsTable.columns.getName("INPUT") + " = '" + input + "', " +
                TestsTable.columns.getName("OUTPUT") + " = '" + output + "', " +
                TestsTable.columns.getName("EXAMPLE") + " = " + (isExample ? 1 : 0) + ", " +
                TestsTable.columns.getName("PUBLIC") + " = " + (isPublic ? 1 : 0);
    }

    public String insertString() {
        return "(" + TestsTable.columns.getName("TASK") + ", " +
                TestsTable.columns.getName("INPUT") + ", " +
                TestsTable.columns.getName("OUTPUT") + ", " +
                TestsTable.columns.getName("EXAMPLE") + ", " +
                TestsTable.columns.getName("PUBLIC") + ") VALUES ('" +
                task + "', '" +  input + "', '" + output + "', " + (isExample ? 1 : 0) + ", " + (isPublic ? 1 : 0) + ")";
    }
}

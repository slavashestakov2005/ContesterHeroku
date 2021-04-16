package com.example.database;

import java.sql.*;
import java.util.Stack;

public class DataBaseHelper {
    private static Connection connection;
    private static Stack<Statement> statements;

    static {
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            String username = System.getenv("JDBC_DATABASE_USERNAME");
            String password = System.getenv("JDBC_DATABASE_PASSWORD");

            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl, username, password);
            statements = new Stack<>();
            statements.push(connection.createStatement());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String sql){
        try {
            synchronized (statements){
                statements.peek().execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql){
        try {
            synchronized (statements) {
                return statements.peek().executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void push(){
        try {
            synchronized (statements) {
                statements.push(connection.createStatement());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void pop(){
        synchronized (statements) {
            statements.pop();
        }
    }

    public static String toSQL(String sql){
        return sql.replaceAll("'", "''");
    }
}

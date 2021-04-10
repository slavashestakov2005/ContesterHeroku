package com.example.database;

import com.example.Root;

import java.sql.*;
import java.util.Stack;

public class DataBaseHelper {
    private static Connection connection;
    private static Stack<Statement> statements;

    static {
        try {
            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + Root.webDirectory  + "/database.db");
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

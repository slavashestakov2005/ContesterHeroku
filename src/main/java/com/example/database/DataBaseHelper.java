package com.example.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Stack;

public class DataBaseHelper {
    private static Connection connection;
    private static Stack<Statement> statements;

    static {
        try {

            URI dbUri = new URI(System.getenv("postgres://ffdvkpmpwhbejq:1c96f772280b265bd04530561ecab2f93eb6a4648f505103de76bf6f6f238386@ec2-176-34-222-188.eu-west-1.compute.amazonaws.com:5432/d6uk1rn2anpli3"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String connectionString = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();


            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connectionString, username, password);
            statements = new Stack<>();
            statements.push(connection.createStatement());
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
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

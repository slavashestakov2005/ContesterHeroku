package com.example.database.tables;

import com.example.database.Columns;
import com.example.database.DataBaseHelper;
import com.example.database.rows.Constant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstantsTable {
    private static final String table = "constants";
    public static final Columns columns;
    private static HashMap<String, String> constants;

    static {
        columns = new Columns();
        columns.add("NAME", 1, "name");     // text     NOT NULL    PK      UNIQUE
        columns.add("VALUE", 2, "value");   // text     NOT NULL
        ArrayList<Constant> constantArrayList = getAllConstants();
        constants = new HashMap<>();
        for(Constant constant : constantArrayList) constants.put(constant.getName(), constant.getValue());
    }

    public static ArrayList<Constant> getAllConstants() {
        ArrayList<Constant> constants = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table);
            if (resultSet != null) {
                while (resultSet.next()) {
                    constants.add(Constant.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return constants;
    }

    public static void updateConstant(Constant constant){
        DataBaseHelper.execute("UPDATE " + table + " SET " + constant.updateString() + " WHERE " + columns.getName("NAME") + " = '" + constant.getName() + "'");
        constants.put(constant.getName(), constant.getValue());
    }

    public static Constant selectByName(String name){
        return new Constant(name, constants.get(name));
    }
}

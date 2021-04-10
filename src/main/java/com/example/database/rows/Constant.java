package com.example.database.rows;

import com.example.database.DataBaseHelper;
import com.example.database.tables.ConstantsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Constant {
    private String name, value;

    private Constant(boolean type, String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Constant(String name, String value) {
        this.name = DataBaseHelper.toSQL(name);
        this.value = DataBaseHelper.toSQL(value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static Constant parseSQL(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(ConstantsTable.columns.getIndex("NAME"));
        String value = resultSet.getString(ConstantsTable.columns.getIndex("VALUE"));
        return new Constant(false, name, value);
    }

    @Override
    public String toString() {
        return "Constant{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String updateString() {
        return ConstantsTable.columns.getName("NAME") + " = '" + name + "', " +
                ConstantsTable.columns.getName("VALUE") + " = '" + value + "'";
    }
}

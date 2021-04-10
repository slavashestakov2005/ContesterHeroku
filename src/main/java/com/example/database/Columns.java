package com.example.database;

import java.util.ArrayList;
import java.util.List;

public class Columns {
    private List<Column> columns;

    public Columns() {
        columns = new ArrayList<>();
    }

    public void add(String tag, int index, String name){
        columns.add(new Column(tag, index, name));
    }

    public int getIndex(String tag){
        for(Column column : columns){
            if (column.tag.equals(tag)) return column.index;
        }
        return -1;
    }

    public String getName(String tag){
        for(Column column : columns){
            if (column.tag.equals(tag)) return column.name;
        }
        return "";
    }

    public static class Column {
        public String tag;
        public int index;
        public String name;

        public Column(String tag, int index, String name) {
            this.tag = tag;
            this.index = index;
            this.name = name;
        }
    }
}

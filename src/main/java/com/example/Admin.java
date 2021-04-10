package com.example;

import com.example.database.tables.ConstantsTable;

public class Admin {
    public static String NAME = "admin_name", SURNAME = "admin_surname";
    private static String adminName, adminSurname;
    static {
        adminName = ConstantsTable.selectByName(NAME).getValue();
        adminSurname = ConstantsTable.selectByName(SURNAME).getValue();
    }

    public static void setName(String name){
        adminName = name;
    }

    public static void setSurname(String surname){
        adminSurname = surname;
    }

    public static boolean checkUser(String name, String surname){
        return adminName.equals(name) && adminSurname.equals(surname);
    }
}

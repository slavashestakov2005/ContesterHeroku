package com.example.runner;

import com.example.Root;
import com.example.database.rows.Lang;

import java.text.MessageFormat;

public class Languages {
    private static long hash;
    private static final Integer x;

    static {
        x = 0;
    }

    public static long getHash(){
        synchronized (x) {
            return hash++;
        }
    }

    public static String generateProgramName(Lang lang, long hash){
        return Root.rootDirectory +  "\\Programs\\" + hash + "." + lang.getEnd2();
    }

    public static String getCompileCommand(Lang lang, String fileName, long hash){
        return MessageFormat.format(lang.getCompileCommand(), fileName, generateProgramName(lang, hash));
    }

    public static String getExecuteCommand(Lang lang, long hash){
        return MessageFormat.format(lang.getExecuteCommand(), generateProgramName(lang, hash));
    }

    public static String generateFileName(Lang lang, long hash){
        return hash + "." + lang.getEnd1();
    }
}

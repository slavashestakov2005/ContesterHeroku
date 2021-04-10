package com.example.runner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Checker {
    public static String read(String fileName){
        try {
            FileInputStream in = new FileInputStream(fileName);
            String line = new String(in.readAllBytes());
            in.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] readFile(String fileName){
        String lines[] = read(fileName).split("[\r\n]+");
        ArrayList<String> result = new ArrayList<>();
        for(String line : lines){
            String words[] = line.trim().split("\\s+");
            if (words.length > 0 && words[0].length() > 0 && words[0].charAt(0) != '#') result.addAll(Arrays.asList(words));
        }
        System.out.print(fileName + ": [");
        for(String s : result) System.out.print("'" + s + "', ");
        System.out.println("]");
        return result.toArray(new String[0]);
    }

    public static boolean equals(String fileName1, String filename2){
        return equals(readFile(fileName1), readFile(filename2));
    }

    public static boolean equals(String[] file1, String fileName2){
        return equals(file1, readFile(fileName2));
    }

    private static boolean equals(String[] file1, String[] file2) {
        return Arrays.equals(file1, file2);
    }

    public static boolean check(String[] correct, String[] answer){
        ArrayList<String> list = new ArrayList<>();
        for(String line : answer){
            String words[] = line.trim().split("\\s+");
            if (words.length > 0 && words[0].length() > 0 && words[0].charAt(0) != '#') list.addAll(Arrays.asList(words));
        }
        return equals(correct, list.toArray(new String[0]));
    }
}

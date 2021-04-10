package com.example.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TaskListParser {
    private static final String WINDOWS = "win", MAC = "mac", NIX = "nix", NUX = "nux", UNIX = "unix", ERROR = "error";
    private static final String os;
    private static List<Integer> parts;
    private static Map<Long, Long> memory;
    private static long lastUpdateTime;
    private static boolean hasNew;
    static {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.contains(WINDOWS)) os = WINDOWS;
        else if (system.contains(MAC)) os = MAC;
        else if (system.contains(NIX) || system.contains(NUX)) os = UNIX;
        else os = ERROR;
        parts = new ArrayList<>();
        memory = new HashMap<>();
        lastUpdateTime = 0;
        hasNew = false;
    }

    public static synchronized void addNew(){
        hasNew = true;
    }

    public static synchronized long getMemory(long pid){
        if (lastUpdateTime + 10 < System.currentTimeMillis() || hasNew) parse();
        boolean b = memory.containsKey(pid);
        System.out.println("Pid : " + pid + " -> " + b);
        return b ? memory.get(pid) : 0;
    }

    private static synchronized void parse(){
        hasNew = false;
        lastUpdateTime = System.currentTimeMillis();
        if (os.equals(WINDOWS)) {
            try {
                parseWin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized void parseWin() throws IOException {
        Process process = Runtime.getRuntime().exec("tasklist.exe");
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "866");
        BufferedReader br = new BufferedReader(isr);
        br.readLine();
        br.readLine();
        String line = br.readLine();
        if (parts.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(String s : line.split(" ")) tmp.add(s.length());
            parts.add(tmp.get(0) + 1);
            parts.add(tmp.get(0) + tmp.get(1) + 1);
            parts.add(tmp.get(0) + tmp.get(1) + tmp.get(2) + tmp.get(3) + 4);
            parts.add(tmp.get(0) + tmp.get(1) + tmp.get(2) + tmp.get(3) + tmp.get(4) + 1);
        }
        Map<Long, Long> tmp = new HashMap<>();
        while ((line = br.readLine()) != null) {
            String pid = line.substring(parts.get(0), parts.get(1)), mem = line.substring(parts.get(2), parts.get(3));
            long pid_l = customParseLong(pid), mem_l = customParseLong(mem);
            tmp.put(pid_l, update(pid_l, mem_l));
        }
        memory = tmp;
    }

    private static synchronized long customParseLong(String s){
        long result = 0, len = s.length();
        for(int i = 0; i < len; ++i){
            if (Character.isDigit(s.charAt(i))){
                result *= 10;
                result += s.charAt(i) - '0';
            }
        }
        return result;
    }

    private static synchronized long update(long pid, long mem){
        if (memory.containsKey(pid)) return Math.max(memory.get(pid), mem);
        return mem;
    }
}

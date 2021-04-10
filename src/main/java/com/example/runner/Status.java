package com.example.runner;

public enum Status{
    OK("Accepted", 1, "green"),
    WA("Wrong answer", 2),
    CE("Compilation error", 3),
    RE("Runtime error", 4),
    MLE("Memory limit exceeded", 5),
    TLE("Time limit exceeded", 6),
    FAIL("Fail", 7);

    private String text, color;
    private int id;

    public static Status fromInt(int id){
        switch (id){
            case 1 : return OK;
            case 2 : return WA;
            case 3 : return CE;
            case 4 : return RE;
            case 5 : return MLE;
            case 6 : return TLE;
            default: return FAIL;
        }
    }

    Status(String text, int id, String color) {
        this.text = text;
        this.id = id;
        this.color = color;
    }

    Status(String text, int id){
        this(text, id, "red");
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return text;
    }

    public String htmlString(){
        return String.format("<font color=\"%s\">%s</font>", color, text);
    }
}
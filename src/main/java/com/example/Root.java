package com.example;

import java.io.File;
import java.nio.file.Paths;

public class Root {
    public static final String rootDirectory, webDirectory;

    static {
        rootDirectory = Paths.get("").toAbsolutePath().toString();
        webDirectory = rootDirectory + "/src/main/webapp";
    }
}

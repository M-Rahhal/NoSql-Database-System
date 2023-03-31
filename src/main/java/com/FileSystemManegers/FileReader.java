package com.FileSystemManegers;

import java.io.*;

public class FileReader {

    public String readFileAsString(String filePath) throws IOException {
        String file = filePath;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line=reader.readLine())!=null)
            builder.append(line);
        return builder.toString();
    }
    public String readFileAsString(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line=reader.readLine())!=null)
            builder.append(line);
        return builder.toString();
    }

}



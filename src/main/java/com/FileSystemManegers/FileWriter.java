package com.FileSystemManegers;

import java.io.*;
import java.util.List;

public class FileWriter {

    public void writeFile(String filePath , String data) throws IOException {
        String file = filePath;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write(data);
        writer.flush();
        writer.close();
    }
    public void writeFile(String filePath , List<String> data) throws IOException {
        String file = filePath;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        for (String d : data)
            writer.write(d);
        writer.flush();
        writer.close();
    }
    public void writeFile(File file , String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write(data);
        writer.flush();
        writer.close();
    }

}

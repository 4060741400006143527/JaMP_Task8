package com.epam.jamp.troubleshooting.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<String> read(File file) throws InterruptedException {
        System.out.println("Reading started");
        List<String> result = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line.substring(0, 3));
                Thread.sleep(30);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return result;
    }
}

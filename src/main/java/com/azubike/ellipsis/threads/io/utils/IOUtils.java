package com.azubike.ellipsis.threads.io.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {
    private static void copy(InputStream src, OutputStream dest) {
        int value;
        try {
            while ((value = src.read()) != -1) {
                dest.write(value);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void copyFiles(String sourceFIle, String destFIle) {
        try (FileInputStream source = new FileInputStream(sourceFIle); FileOutputStream target = new FileOutputStream(destFIle)) {
            IOUtils.copy(source, target);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Integer> patternFinder(File file, String pattern) {
        List<Integer> lineNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (line.contains(pattern)) {
                    lineNumbers.add(lineNumber);
                }
                lineNumber++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lineNumbers;
    }

}
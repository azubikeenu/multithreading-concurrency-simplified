package com.azubike.ellipsis.threads.io.utils;

import java.io.*;

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
}
package com.azubike.ellipsis.threads;

import com.azubike.ellipsis.threads.io.utils.IOUtils;

// Using a Class Type Runnable
class CopyTask implements Runnable {
    private String srcFile;
    private String destFile;

    public CopyTask(String srcFile, String destFile) {
        this.srcFile = srcFile;
        this.destFile = destFile;
    }

    @Override
    public void run() {
        IOUtils.copyFiles(this.srcFile, this.destFile);
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        String a = "a.txt";
        String b = "b.txt";
        String c = "c.txt";
        String d = "d.txt";
        new Thread(ThreadExample.copyTask(a,c)).start();
        new Thread(ThreadExample.copyTask(b,d)).start();
    }


    // Using a Method defined Runnable
    private static Runnable copyTask(String srcFile, String destFile) {
        return () -> {
            IOUtils.copyFiles(srcFile, destFile);
        };
    }

}

package com.azubike.ellipsis.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        String a = "a.txt";
        String b = "b.txt";
        String c = "c.txt";
        String d = "d.txt";
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(ThreadExample.copyTask(a,c));
        executorService.execute(ThreadExample.copyTask(b,d));
        executorService.shutdown();

    }

}

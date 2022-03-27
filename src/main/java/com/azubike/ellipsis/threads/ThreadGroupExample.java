package com.azubike.ellipsis.threads;

import java.util.Arrays;

class TaskThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadGroupExample {
    public static void main(String[] args) {
        final ThreadGroup myGroup = new ThreadGroup("myGroup");
        myGroup.setMaxPriority(7);
        System.out.println("System threads ---------------");
        Thread newThread = new Thread(myGroup, new TaskThread(), "Demo Thread");

        newThread.start();

        Thread currentThread = Thread.currentThread();
        ThreadGroup threadGroup = currentThread.getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        threadGroup.list();



    }
}

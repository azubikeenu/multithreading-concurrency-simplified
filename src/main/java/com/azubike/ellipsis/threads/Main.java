package com.azubike.ellipsis.threads;

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.print("H");
        }
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.print("L");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        Thread t2 = new Thread(new MyTask());
        t2.start();
        for (int i = 0; i < 100; i++) {
            System.out.print("K");
        }
    }
}

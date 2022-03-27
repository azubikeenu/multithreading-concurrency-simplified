package com.azubike.ellipsis.threads;
/*
* Thread priorities ranges between 1 - 10
* Thread.MIN_PRIORITY - 1
* Thread.NORM_PRIORITY - 5
* Thread.MAX_PRIORITY - 10
* */

public class ThreadPriorityExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(true){
                System.out.print("C");
            }
        });
        Thread t2 = new Thread(()->{
            while(true){
                System.out.print("T");
            }
        });
        t1.setPriority(Thread.NORM_PRIORITY + 3);
        t1.start();
        t2.start();

    }
}

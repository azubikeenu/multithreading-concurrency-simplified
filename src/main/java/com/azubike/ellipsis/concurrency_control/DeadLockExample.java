package com.azubike.ellipsis.concurrency_control;

/**
 * Two threads are said to be in a state of Deadlock when both threads are circularly waiting over the lock of a shared resource
 * <p> A program simulating Deadlocks</p>
 * <p> To solve a deadlock scenario make sure that each thread locks the resource in the same sequence </p>
 */
class WriterOne extends Thread {
    private final Object book;
    private final Object pen;

    WriterOne(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized (book) {
            // simulate a high computing process
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            synchronized (pen) {
                System.out.println("Writer one is Writing");
            }
        }
    }
}

class WriterTwo extends Thread {
    private final Object book;
    private final Object pen;

    WriterTwo(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized (pen) {
            // simulate a high computing process
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            synchronized (book) {
                System.out.println("Writer Two is Writing");
            }
        }
    }
}

public class DeadLockExample {
    public static void main(String[] args) {
        Object book = new Object();
        Object pen = new Object();
        final WriterOne w1 = new WriterOne(book, pen);
        final WriterTwo w2 = new WriterTwo(book, pen);
        w1.start();
        w2.start();
        try {
            w1.join();
            w2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Main thread is done");
    }
}

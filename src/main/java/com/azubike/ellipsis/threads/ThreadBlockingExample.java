package com.azubike.ellipsis.threads;

class BlockedThread extends Thread {
    @Override
    public void run() {
        for (; ; ) {
            if (interrupted()) {
                System.out.println("Thread interrupted exiting");
                break;
            }
            System.out.println("---");
        }
    }
}

public class ThreadBlockingExample {
    public static void main(String[] args) {
        System.out.println("Print line One");
        final BlockedThread blockedThread = new BlockedThread();
        blockedThread.start();
        try {
            Thread.sleep(2000L);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        blockedThread.interrupt();
        System.out.println("Print Line Two");

    }
}

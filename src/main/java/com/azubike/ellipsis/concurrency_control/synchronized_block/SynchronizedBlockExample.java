package com.azubike.ellipsis.concurrency_control.synchronized_block;
/**
 * When compared to  synchronized methods sync blocks are always the better option
 * */

class SampleObject {
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incrementX() {
        // for simulation of resource consuming computing task
        int y = x + 1;
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        setX(y);

    }
}


class SampleThread extends Thread {
    private final SampleObject obj;

    SampleThread(SampleObject obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            obj.incrementX();
        }

    }
}


public class SynchronizedBlockExample {
    public static void main(String[] args) {
        SampleObject object = new SampleObject();
        object.setX(10);
        SampleThread t1 = new SampleThread(object);
        SampleThread t2 = new SampleThread(object);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(object.getX());
    }
}

package com.azubike.ellipsis.concurrency_control.reentrant_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SampleObject {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incrementX() {
        // for simulation of resource consuming computing task
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            int y = getX();
            y++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            setX(y);
        } finally {
            lock.unlock();
        }
    }
}

class SampleThread extends Thread {
    private SampleObject obj;

    SampleThread(SampleObject obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.incrementX();
    }
}

public class ReentrantLocksExample {
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
        } catch (
                InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(object.getX());
    }
}

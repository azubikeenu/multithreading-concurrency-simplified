package com.azubike.ellipsis.concurrency_packages;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
    private BlockingQueue<Integer> messageQueue;

    Producer(BlockingQueue<Integer> queue) {
        this.messageQueue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                messageQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Produced " + i);
        }
    }
}

class Consumer extends Thread {
    private BlockingQueue<Integer> messageQueue;

    Consumer(BlockingQueue<Integer> queue) {
        this.messageQueue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                messageQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumed " + i);
        }
    }
}

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> messageQueue = new ArrayBlockingQueue<>(3);
        new Producer(messageQueue).start();
        new Consumer(messageQueue).start();
    }
}

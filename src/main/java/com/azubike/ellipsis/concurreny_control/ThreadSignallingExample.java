package com.azubike.ellipsis.concurreny_control;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Wait(), Notify() and NotifyAll()</h2>
 * wait() , notify() and notifyAll() are instance methods of the Thread class that facilitates communication between threads
 * in the same group they are only valid in a synchronized context
 * <p>wait() this places the thread on an indefinite wait state</p>
 * <p>wait(time:millSecs) this places the thread on a timed  state</p>
 * <p>notify() terminates the wait stage of a single thread waiting for the shared object</p>
 * <p>notifyAll() terminates the wait stage of all threads waiting for the shared object</p>
 */
class MessageQueue {
    private int limit;
    private List<Integer> messages = new ArrayList<>();


    MessageQueue(int limit) {
        this.limit = limit;
    }

    public boolean isEmpty() {
        return this.messages.size() == 0;
    }

    public boolean isFull() {
        return this.messages.size() == limit;
    }

    public synchronized void queue(int value) {
         while(isFull()) {
           try{
               // wait for the consumer to data in the Buffer
               this.wait();
           }catch(InterruptedException ex){
               ex.printStackTrace();
           }
        }
        messages.add(value);
         notify(); // notify the consumer
    }

    public synchronized int dequeue() {
        while (isEmpty()) {
            try{
                // wait for the  producer to add data to the Buffer
                this.wait();
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        final int message = messages.remove(0);
        this.notify(); // notify the producer
        return message;
    }

}

class Producer extends Thread {
    private MessageQueue messageQueue;

    Producer(MessageQueue queue) {
        this.messageQueue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            messageQueue.queue(i);
            System.out.println("Produced " + i);
        }
    }
}

class Consumer extends Thread {

    private MessageQueue messageQueue;

    Consumer(MessageQueue queue) {
        this.messageQueue = queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            final int message = messageQueue.dequeue();
            System.out.println("Consumed " + message);
        }
    }

}

public class ThreadSignallingExample {

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(3);
        new Producer(messageQueue).start();
        new Consumer(messageQueue).start();
    }

}

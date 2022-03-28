package com.azubike.ellipsis.threads;

/**
 * A daemon thread is one that doesnt block the termination of the main thread , ie
 * its termination is tied to the termination of the main thread
 * It is usually applied to perform specific tasks within an application
 * They are very handy for performing background tasks or clean up tasks
 */
public class DaemonThreadExample {
    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> {
            while (true) {
                System.out.print("*");
            }
        }, "daemon thread");
        t1.setDaemon(true);
        t1.start();

        for (int i = 0; i < 10; i++) {
            System.out.print(i + ",");
        }
        //----Print all daemon threads ----------//
        Thread currentThread = Thread.currentThread();
        ThreadGroup threadGroup = currentThread.getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        Thread[] threads = new Thread[10];
        final int n = threadGroup.enumerate(threads); // this lists the threads in a given group and copies their reference tp the array object; it also returns the total number of copied references
        for (int i = 0; i < n; i++) {
            if(threads[i].isDaemon()) System.out.println(threads[i].getName());
        }

       // Arrays.stream(threads).filter(Thread::isDaemon).forEach(thread -> System.out.println(thread.getName()));
    }
}

package com.azubike.ellipsis.threads;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

class MathClass {
    static int sumNumbers(int start, int end) {
        return IntStream.range(start, end).boxed().reduce(0, Integer::sum);
    }
}

public class CallableExample {
    public static void main(String[] args) {
        int start = 1;
        int end = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Future<Integer> future = executorService.submit(() -> MathClass.sumNumbers(start, end));
        // perform parallel task
        while (!future.isDone()) {}

        try {
            final Integer result = future.get();
            System.out.println("The result is " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


    }
}

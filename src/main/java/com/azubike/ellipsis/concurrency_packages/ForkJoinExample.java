package com.azubike.ellipsis.concurrency_packages;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork-Join pool is a type of Executor for ForkJoin task
 * It differs from ExecutorService by virtue of employing work stealing
 * In ForkJoin , each task is recursively split into smaller tasks and the join operation waits for the completion of each task
 * and combines the obtained results
 */

class SearchTask extends RecursiveTask<Integer> {
    private int[] arr;
    private int start;
    private int end;
    private int searchElement;

    SearchTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread());
        int size = end - start;
        if (size > 3) {
            int midpoint = (start + end) / 2;
            final SearchTask taskOne = new SearchTask(arr, start, midpoint, searchElement);
            final SearchTask taskTwo = new SearchTask(arr, midpoint, end, searchElement);
            taskOne.fork();
            taskTwo.fork();
            return taskOne.join() + taskTwo.join();

        } else
            return searchOperation();
    }


    public int searchOperation() {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (searchElement == arr[i]) count++;
        }
        return count;
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 6, 6, 6, 9, 10, 1};
        int start = 0;
        int end = arr.length;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        final int searchElement = 6;
        SearchTask searchTask = new SearchTask(arr, start, end, searchElement);
        final Integer result = pool.invoke(searchTask);
        System.out.println(searchElement + " was found " + result + " times");
    }
}

package com.azubike.ellipsis.threads;

import com.azubike.ellipsis.threads.io.utils.IOUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PatternSearch {

    public static void main(String[] args) {
        String pattern = "public";
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        File dir = new File("./src/sample");
        final File[] files = Objects.requireNonNull(dir.listFiles());
        long startTime = System.currentTimeMillis();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            for (File file : files) {
                final Future<List<Integer>> future = executorService.submit(() -> IOUtils.patternFinder(file, pattern));
                resultMap.put(file.getName(), future);
            }
            waitForAll(resultMap);
            resultMap.forEach((k, v) -> {
                List<Integer> lineNumbers = (List<Integer>) v;
                if (!lineNumbers.isEmpty())
                    System.out.println(pattern + " found in lines" + lineNumbers + " in " + k);
            });
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Total time taken " + totalTime);
        } finally {
            executorService.shutdown();
        }

    }

    private static void waitForAll(Map<String, Object> resultMap) {
        resultMap.forEach((fileName, future) -> {
            Future<List<Integer>> f = (Future<List<Integer>>) future;
            while (!f.isDone()){
                Thread.yield();
            }
            try {
                resultMap.put(fileName, f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

package com.epam.jamp.troubleshooting.three;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Fork left = new Fork();
        Fork right = new Fork();
        Fork first = left;

        Philosopher philosopher;
        for (int i = 1; i <= 4; ++i) {
            philosopher = new Philosopher(left, right);
            left = right;
            right = new Fork();
            executorService.submit(philosopher);
        }

//        philosopher = new Philosopher(left, first);
        philosopher = new Philosopher(first, left); // without deadlock
        executorService.submit(philosopher);

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        System.out.println("Main finished");
    }
}
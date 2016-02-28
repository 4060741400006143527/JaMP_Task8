package com.epam.jamp.troubleshooting.two;

import com.epam.jamp.troubleshooting.threads.MyThread;
import com.epam.jamp.troubleshooting.threads.SimpleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        executorService.submit(new MyThread(lock1, lock2));
        executorService.submit(new MyThread(lock2, lock1));

        for (int i = 1; i <= 3; ++i) {
            executorService.submit(new SimpleThread(lock1));
            executorService.submit(new SimpleThread(lock2));
        }

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        System.out.println("Main finished");
    }
}
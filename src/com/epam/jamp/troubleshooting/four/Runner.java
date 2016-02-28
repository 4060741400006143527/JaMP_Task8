package com.epam.jamp.troubleshooting.four;


import com.epam.jamp.troubleshooting.threads.SimpleThread;
import com.epam.jamp.troubleshooting.threads.SlowThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Lock lock = new ReentrantLock();

        executorService.execute(new SlowThread(lock));

        for (int i = 1; i <= 4; i++) {
            executorService.submit(new SimpleThread(lock));
        }

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        System.out.println("Main finished");
    }
}

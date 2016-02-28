package com.epam.jamp.troubleshooting.threads;

import java.util.concurrent.locks.Lock;

public class MyThread implements Runnable {

    private final Lock lock1;
    private final Lock lock2;

    public MyThread(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName + " acquired lock on lock_1: " + lock1);
            try {
                Thread.sleep(5000);
                lock2.lock();
                System.out.println(threadName + " acquired lock on lock_2: " + lock2);
            } catch (InterruptedException e) {
                System.err.println("Thread '" + threadName + "' interrupted");
            } finally {
                System.out.println(threadName + " released lock_2: " + lock2);
                lock2.unlock();
            }
        } finally {
            System.out.println(threadName + " released lock_1: " + lock1);
            lock1.unlock();
        }
    }
}

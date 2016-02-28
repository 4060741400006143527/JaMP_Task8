package com.epam.jamp.troubleshooting.threads;

import java.util.concurrent.locks.Lock;

public class SimpleThread implements Runnable {

    private final Lock lock;

    public SimpleThread(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread '" + threadName + "' started execution");
        try {
            Thread.sleep(6000);
            lock.lock();
            System.out.println("Thread '" + threadName + "' is executing");
        } catch (InterruptedException e) {
            System.err.println("Thread '" + threadName + "' interrupted");
        } finally {
            lock.unlock();
            System.out.println("Thread '" + threadName + "' finished execution");
        }
    }
}

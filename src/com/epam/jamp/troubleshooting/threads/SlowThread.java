package com.epam.jamp.troubleshooting.threads;

import java.util.concurrent.locks.Lock;

public class SlowThread implements Runnable {

    private final Lock lock;

    public SlowThread(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Slow thread started execution");
        try {
            lock.lock();
            Thread.sleep(30000);
            System.out.println("Slow thread is executing");
        } catch (InterruptedException e) {
            System.err.println("Slow thread interrupted");
        } finally {
            lock.unlock();
            System.out.println("Slow thread finished execution");
        }
    }
}

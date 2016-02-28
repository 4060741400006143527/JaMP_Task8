package com.epam.jamp.troubleshooting.three;

import java.util.Random;

public class Philosopher implements Runnable {

    private static Random random = new Random();
    private static int counter = 0;

    private int number = counter++;

    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(final Fork leftFork, final Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    public void think() {
        System.out.println(this + " thinking");
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            System.err.println(this + " interrupted");
            throw new RuntimeException(e);
        }
    }

    public void eat() {
        synchronized (leftFork) {
            System.out.println(this + " picked up " + this.leftFork + ". Waiting for " + this.rightFork);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.err.println(this + " interrupted");
            }
            synchronized (rightFork) {
                System.out.println(this + " eating");
            }
        }
    }

    public String toString() {
        return "Philosopher " + number;
    }
}
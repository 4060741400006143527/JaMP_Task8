package com.epam.jamp.troubleshooting.three;

public class Fork {

    private static int counter = 0;

    private int number = counter++;

    @Override
    public String toString() {
        return "Fork " + number;
    }
}

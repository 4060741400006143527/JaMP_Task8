package com.epam.jamp.troubleshooting.five;

import java.io.File;

public class Runner {

    public static void main(String[] args) throws Exception {

        new FileReader().read(new File("data.txt"));
    }
}

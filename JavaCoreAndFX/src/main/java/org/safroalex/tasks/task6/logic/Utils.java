package org.safroalex.tasks.task6.logic;

import java.util.Random;

public class Utils {
    public static void pause(int lowerBound, int upperBound) {
        try {
            Thread.sleep(new Random().nextInt(upperBound - lowerBound) + lowerBound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package org.safroalex.tasks.task6.logic;

import java.util.Random;

/**
 * Утилитный класс, предоставляющий общие вспомогательные методы.
 */
public class Utils {

    /**
     * Приостанавливает выполнение текущего потока на случайный период времени
     * между заданными нижней и верхней границами.
     *
     * @param lowerBound минимальное количество миллисекунд задержки.
     * @param upperBound максимальное количество миллисекунд задержки.
     */
    public static void pause(int lowerBound, int upperBound) {
        try {
            Thread.sleep(new Random().nextInt(upperBound - lowerBound) + lowerBound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

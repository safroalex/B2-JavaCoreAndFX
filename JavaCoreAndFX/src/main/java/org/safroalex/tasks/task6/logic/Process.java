package org.safroalex.tasks.task6.logic;

import org.safroalex.tasks.task6.Task6UI;

import java.util.Random;

public class Process {

    private static Task6UI task6UI;
    private static State state = State.RUNNING;
    private static final Object mutex = new Object();
    private static Thread abstractProgram = new Thread(new AbstractProgram());

    public static void setTask6UI(Task6UI task6UI) {
        Process.task6UI = task6UI;
    }

    static class AbstractProgram implements Runnable {

        @Override
        public void run() {
            Thread daemon = new Thread(() -> {
                while (true) {
                    Utils.pause(1000, 5000);
                    if (abstractProgram.isInterrupted()) {
                        break;
                    }
                    synchronized (mutex) {
                        //System.out.println("Демон: Состояние программы сейчас " + state.toString());
                        state = State.values()[new Random().nextInt(State.values().length)];
                        if (state.equals(State.RUNNING)) {
                            System.out.println("Демон:  Состояние не изменилось - RUNNING");
                            task6UI.logToOutputArea("Демон:  Состояние не изменилось - RUNNING");
                        } else {
                            System.out.println("Демон: Состояине изменилось на: " + state.toString());
                            task6UI.logToOutputArea("Демон: Состояине изменилось на: " + state.toString());
                        }
                        mutex.notify();
                    }
                }
            });
            daemon.setDaemon(true);
            daemon.start();
            System.out.println("Абстрактная программа: Запуск инициализирован, демон запущен.");
            task6UI.logToOutputArea("Абстрактная программа: Запуск инициализирован, демон запущен.");

            while (!Thread.currentThread().isInterrupted()) {
                someWork();
            }
        }

        private void someWork() {
            int work = 0;
            work++;
        }
    }

    public static class Supervisor implements Runnable {

        @Override
        public void run() {
            System.out.println("Супервизор: Запуск инициализирован.");
            task6UI.logToOutputArea("Супервизор: Запуск инициализирован.");
            if (abstractProgram.getState() == Thread.State.TERMINATED) {
                abstractProgram = new Thread(new AbstractProgram());
            }
            abstractProgram.start();
            while (!abstractProgram.isInterrupted()) {
                synchronized (mutex) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (state) {
                        case FATAL_ERROR -> stopProgram();
                        case UNKNOWN, STOPPING -> runProgram();
                        default -> {
                            System.out.println("Супервизор: Наблюдаю.");
                            task6UI.logToOutputArea("Супервизор: Наблюдаю.");
                        }
                    }
                }
            }
        }

        private void runProgram() {
            state = State.RUNNING;
            System.out.println("Супервизор: Перезапуск программы.");
            task6UI.logToOutputArea("Супервизор: Перезапуск программы.");
        }

        private void stopProgram() {
            abstractProgram.interrupt();
            System.out.println("Супервизор: Остановка программы.");
            task6UI.logToOutputArea("Супервизор: Остановка программы.");
        }
    }

}
package org.safroalex.tasks.task6.logic;

import org.safroalex.tasks.task6.Task6UI;

import java.util.Random;

public class Process {

    private static Task6UI task6UI;
    private static State state = State.RUNNING;
    private static final Object mutex = new Object();
    private static Thread abstractProgram = new Thread(new AbstractProgram());
    private static int indexOfLuck = 0;

    public static void setTask6UI(Task6UI task6UI) {
        Process.task6UI = task6UI;
    }

    public static int getIndexOfLuck() {
        return Process.indexOfLuck;
    }

    /**
     * Класс AbstractProgram реализует интерфейс Runnable и представляет собой абстрактное представление
     * программы, которая может исполняться в отдельном потоке. Этот класс включает в себя механизм
     * демон-потока для асинхронного обновления состояния программы. Состояние обновляется
     * случайным образом в определенные интервалы времени и синхронизируется с основной программой
     * и пользовательским интерфейсом с использованием мьютекса.
     */
    static class AbstractProgram implements Runnable {
        /**
         * Запускает демон-поток, который периодически обновляет состояние программы.
         * Демон работает в фоновом режиме и в случайные интервалы времени изменяет состояние
         * на одно из значений перечисления State. При каждом обновлении состояния,
         * оно выводится в консоль и логируется в пользовательском интерфейсе.
         * Если состояние программы остается прежним (RUNNING), выводится специальное уведомление.
         * Поток демона может быть прерван, если поток abstractProgram будет прерван.
         * Состояние программы и пользовательский интерфейс контролируются извне
         * и синхронизируются с помощью объекта mutex.
         */
        @Override
        public void run() {
            Thread.currentThread().setName("MainAbstract");
            Thread daemon = new Thread(() -> {
                Thread.currentThread().setName("DaemonAbstract");
                while (true) {
                    Utils.pause(1000, 5000);
                    if (abstractProgram.isInterrupted()) {
                        break;
                    }
                    synchronized (mutex) {
                        if (task6UI != null) System.out.println("Демон: Состояние программы сейчас " + state.toString());
                        state = State.values()[new Random().nextInt(State.values().length)];
                        if (state.equals(State.RUNNING)) {
                            System.out.println("Демон:  Состояние не изменилось - RUNNING");
                            if (task6UI != null) task6UI.logToOutputArea("Демон:  Состояние не изменилось - RUNNING");
                        } else {
                            System.out.println("Демон: Состояине изменилось на: " + state.toString());
                            if (task6UI != null) task6UI.logToOutputArea("Демон: Состояине изменилось на: " + state.toString());
                        }
                        mutex.notify();
                    }
                }
            });
            daemon.setDaemon(true);
            daemon.start();
            System.out.println("Абстрактная программа: Запуск инициализирован, демон запущен.");
            if (task6UI != null) task6UI.logToOutputArea("Абстрактная программа: Запуск инициализирован, демон запущен.");

            while (!Thread.currentThread().isInterrupted()) {
                someWork();
            }
        }

        private void someWork() {
            indexOfLuck++;
        }
    }

    /**
     * Класс Supervisor представляет собой реализацию интерфейса Runnable,
     * которая отвечает за наблюдение и управление состоянием выполнения
     * абстрактной программы. Класс запускает и контролирует жизненный цикл
     * потока abstractProgram, реагируя на его состояние и управляя его выполнением
     * в зависимости от текущего состояния программы.
     */
    public static class Supervisor implements Runnable {

        /**
         * Запускает логику супервизора, который контролирует состояние абстрактной программы.
         * Супервизор отслеживает изменения состояния и реагирует на них соответствующим образом.
         * Если обнаружено состояние FATAL_ERROR, выполняется остановка программы. При состоянии
         * UNKNOWN или STOPPING запускается новая программа. В остальных случаях супервизор просто
         * продолжает наблюдение.
         */
        @Override
        public void run() {
            Thread.currentThread().setName("Supervisor");
            System.out.println("Супервизор: Запуск инициализирован.");
            if (task6UI != null) task6UI.logToOutputArea("Супервизор: Запуск инициализирован.");
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
                            if (task6UI != null) task6UI.logToOutputArea("Супервизор: Наблюдаю.");
                        }
                    }
                }
            }
        }

        /**
         * Перезапускает программу, устанавливая её состояние в RUNNING.
         * Сообщает о действии в консоль и логирует в пользовательский интерфейс.
         */
        private void runProgram() {
            state = State.RUNNING;
            System.out.println("Супервизор: Перезапуск программы.");
            //task6UI.logToOutputArea("Супервизор: Перезапуск программы.");
        }

        /**
         * Останавливает программу, прерывая поток abstractProgram.
         * Сообщает о действии в консоль и логирует в пользовательский интерфейс.
         */
        private void stopProgram() {
            abstractProgram.interrupt();
            System.out.println("Супервизор: Остановка программы.");
            if (task6UI != null) task6UI.logToOutputArea("Супервизор: Остановка программы.");
        }
    }

}
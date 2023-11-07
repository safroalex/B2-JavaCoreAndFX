package org.safroalex.tasks.task6;

import org.safroalex.tasks.task6.logic.Process;

public class Task6Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("Main");
        Process.Supervisor supervisor = new Process.Supervisor();
        Thread supervisorThread = new Thread(supervisor);
        supervisorThread.start();

        try {
            supervisorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("What he have done: " + Process.getIndexOfLuck());
    }
}

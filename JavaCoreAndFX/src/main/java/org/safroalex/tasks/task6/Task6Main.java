package org.safroalex.tasks.task6;

import org.safroalex.tasks.task6.logic.*;
import org.safroalex.tasks.task6.logic.Process;

public class Task6Main {
    public static void main(String[] args) {
        Process.Supervisor supervisor = new Process.Supervisor();
        Thread supervisorThread = new Thread(supervisor);
        supervisorThread.start();
    }
}

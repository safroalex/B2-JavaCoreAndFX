package org.safroalex.tasks.task6;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import org.safroalex.tasks.task6.logic.Process;
import org.safroalex.ui.MainWindow;
import org.safroalex.tasks.task6.logic.*;

public class Task6UI {

    private TextArea outputArea;

    private final MainWindow mainWindow;

    public Task6UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public VBox initialize() {
        VBox vBox = new VBox(10);

        outputArea = new TextArea();

        Process.Supervisor[] supervisors = new Process.Supervisor[1];
        Thread[] threadSupervisors = new Thread[1];

        supervisors[0] = new Process.Supervisor();
        threadSupervisors[0] = new Thread(supervisors[0]);

        Button startAlgoButton = new Button("Запустить супервизора.");
        startAlgoButton.setOnAction(e -> {
            if (threadSupervisors[0].getState() == Thread.State.TERMINATED) {
                supervisors[0] = new Process.Supervisor();
                threadSupervisors[0] = new Thread(supervisors[0]);
            }
            threadSupervisors[0].start();
        });

        Button backButton = new Button("Вернуться в главное меню.");
        backButton.setOnAction(e -> mainWindow.showMainMenu());

        vBox.getChildren().addAll(startAlgoButton, backButton, outputArea);

        return vBox;
    }
}

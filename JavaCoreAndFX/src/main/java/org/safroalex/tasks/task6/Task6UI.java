package org.safroalex.tasks.task6;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.safroalex.tasks.task6.logic.Process;
import org.safroalex.ui.MainWindow;

public class Task6UI {

    private TextArea outputArea = new TextArea();

    private final MainWindow mainWindow;

    public Task6UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void logToOutputArea(String message) {
        Platform.runLater(() -> outputArea.appendText(message + "\n"));
    }
    public BorderPane initialize() {
        outputArea.setEditable(false);
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15,12,15,12));
        Process.setTask6UI(this);

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


        // STYLES
        startAlgoButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        startAlgoButton.setStyle("-fx-background-color: #00A676;");
        BorderPane.setAlignment(startAlgoButton, Pos.CENTER);


        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        backButton.setStyle("-fx-background-color: #3C91E6;");
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);


        outputArea.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        outputArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        outputArea.setMinHeight(300);
        outputArea.setMinWidth(200);



        vBox.getChildren().addAll(outputArea, startAlgoButton, backButton);

        borderPane.setCenter(vBox);

        borderPane.setBottom(backButton);

        return borderPane;
    }
}

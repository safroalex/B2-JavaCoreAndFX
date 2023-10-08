package org.safroalex.root;

import javafx.application.Application;
import javafx.stage.Stage;
import org.safroalex.ui.MainWindow;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        new MainWindow().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

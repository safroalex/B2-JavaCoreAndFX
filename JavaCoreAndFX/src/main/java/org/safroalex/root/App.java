package org.safroalex.root;

import javafx.application.Application;
import javafx.stage.Stage;
import org.safroalex.ui.MainWindow;

/**
 * Основной класс приложения, который наследует от javafx.application.Application.
 * Этот класс является точкой входа в JavaFX приложение.
 */
public class App extends Application {

    /**
     * Метод start() вызывается при запуске JavaFX приложения.
     * В этом методе происходит инициализация главного окна приложения.
     *
     * @param primaryStage Основной контейнер Stage для приложения.
     */
    @Override
    public void start(Stage primaryStage) {

        new MainWindow().start(primaryStage);
    }

    /**
     * Главный метод приложения, который запускается при старте программы.
     * Этот метод вызывает метод launch(), который инициирует JavaFX приложение.
     *
     * @param args Массив строковых аргументов командной строки.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

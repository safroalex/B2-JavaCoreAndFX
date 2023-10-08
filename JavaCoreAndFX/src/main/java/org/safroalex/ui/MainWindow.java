package org.safroalex.ui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.safroalex.utils.AudioThreadStart;
import org.safroalex.tasks.task1.Task1UI;
import org.safroalex.tasks.task2.Task2UI;
import org.safroalex.tasks.task5.Task5UI;

public class MainWindow {

    private void initializeMainMenu() {
        // Создание меню
        MenuBar menuBar = new MenuBar();

        // Меню для лабораторных работ
        Menu tasksMenu = new Menu("Лабораторные");
        MenuItem task1 = new MenuItem("Лабораторная 1");
        MenuItem task2 = new MenuItem("Лабораторная 2");
        MenuItem task5 = new MenuItem("Лабораторная 5");
        tasksMenu.getItems().addAll(task1, task2, task5);
        menuBar.getMenus().add(tasksMenu);

        // Обработчики событий для меню
        task1.setOnAction(e -> showTask1UI());
        task2.setOnAction(e -> showTask2UI());
        task5.setOnAction(e -> showTask5UI());

        currentVBox.getChildren().setAll(menuBar);
    }

    private VBox currentVBox;  // Текущий интерфейс в окне

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Лабораторные работы");

        // Инициализация currentVBox
        currentVBox = new VBox();

        // Запуск аудио в новом потоке
        Thread audioThread = new Thread(new AudioThreadStart());
        audioThread.start();

        initializeMainMenu();

        // Размещение меню в окне
        Scene scene = new Scene(currentVBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showTask1UI() {
        Task1UI task1UI = new Task1UI(this);
        BorderPane task1VBox = task1UI.initialize();
        // Заменяем текущий интерфейс на интерфейс задания 1
        currentVBox.getChildren().setAll(task1VBox.getChildren());
    }

    private void showTask2UI() {
        Task2UI task2UI = new Task2UI(this);  // Создаем новый объект Task2UI
        VBox task2VBox = task2UI.initialize();  // Инициализируем его
        // Заменяем текущий интерфейс на интерфейс задания 2
        currentVBox.getChildren().setAll(task2VBox.getChildren());
    }

    private void showTask5UI() {
        Task5UI task5UI = new Task5UI(this);  // Создаем новый объект Task5UI
        VBox task5VBox = task5UI.initialize();  // Инициализируем его
        // Заменяем текущий интерфейс на интерфейс задания 5
        currentVBox.getChildren().setAll(task5VBox.getChildren());
    }

    public void showMainMenu() {
        initializeMainMenu();
    }

}

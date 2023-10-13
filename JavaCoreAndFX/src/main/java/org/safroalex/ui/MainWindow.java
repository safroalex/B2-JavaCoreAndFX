package org.safroalex.ui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import org.safroalex.tasks.task3.Task3UI;
import org.safroalex.tasks.task6.Task6UI;
import org.safroalex.utils.AudioThreadStart;
import org.safroalex.tasks.task1.Task1UI;
import org.safroalex.tasks.task2.Task2UI;
import org.safroalex.tasks.task4.Task4UI;
import org.safroalex.tasks.task5.Task5UI;

public class MainWindow {

    private void initializeMainMenu() {
        // Создание меню
        MenuBar menuBar = new MenuBar();

        // Меню для лабораторных работ
        Menu tasksMenu = new Menu("Лабораторные");
        MenuItem task1 = new MenuItem("Лабораторная 1");
        MenuItem task2 = new MenuItem("Лабораторная 2");
        MenuItem task3 = new MenuItem("Лабораторная 3");
        MenuItem task4 = new MenuItem("Лабораторная 4");
        MenuItem task5 = new MenuItem("Лабораторная 5");
        MenuItem task6 = new MenuItem("Лабораторная 6");
        tasksMenu.getItems().addAll(task1, task2, task3, task4, task5, task6);
        menuBar.getMenus().add(tasksMenu);

        // Обработчики событий для меню
        task1.setOnAction(e -> showTask1UI());
        task2.setOnAction(e -> showTask2UI());
        task3.setOnAction(e -> showTask3UI());
        task4.setOnAction(e -> showTask4UI());
        task5.setOnAction(e -> showTask5UI());
        task6.setOnAction(e -> showTask6UI());

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

    private void showTask3UI() {
        Task3UI task3UI = new Task3UI(this);  // Создаем новый объект Task2UI
        VBox task3VBox = task3UI.initialize();  // Инициализируем его
        // Заменяем текущий интерфейс на интерфейс задания 2
        currentVBox.getChildren().setAll(task3VBox.getChildren());
    }

    private void showTask4UI() {
        Task4UI task4UI = new Task4UI(this);
        VBox task4VBox = task4UI.initialize();
        currentVBox.getChildren().setAll(task4VBox.getChildren());
    }

    private void showTask5UI() {
        Task5UI task5UI = new Task5UI(this);  // Создаем новый объект Task5UI
        VBox task5VBox = task5UI.initialize();  // Инициализируем его
        // Заменяем текущий интерфейс на интерфейс задания 5
        currentVBox.getChildren().setAll(task5VBox.getChildren());
    }

    private void showTask6UI() {
        Task6UI task6UI = new Task6UI(this);
        VBox task6VBox = task6UI.initialize();
        currentVBox.getChildren().setAll(task6VBox.getChildren());
    }

    public void showMainMenu() {
        initializeMainMenu();
    }

}

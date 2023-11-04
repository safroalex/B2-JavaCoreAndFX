package org.safroalex.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import org.safroalex.tasks.task3.Task3UI;
import org.safroalex.tasks.task6.Task6UI;
import org.safroalex.utils.AudioThreadStart;
import org.safroalex.tasks.task1.Task1UI;
import org.safroalex.tasks.task2.Task2UI;
import org.safroalex.tasks.task4.Task4UI;
import org.safroalex.tasks.task5.Task5UI;

public class MainWindow {

    // Создаем BorderPane как основной макет
    BorderPane mainLayout = new BorderPane();
    private final Label polytechLabel;
    private VBox menu;

    public MainWindow() {
        polytechLabel = new Label("POLYTECH");
        polytechLabel.setFont(new Font("Verdana", 100));
        polytechLabel.setTextFill(Color.WHITE);
        mainLayout.setCenter(polytechLabel); // Устанавливаем по умолчанию
    }
    private void initializeMainMenu() {
        // Создание меню
        MenuBar menuBar = new MenuBar();

        menuBar.setStyle("-fx-background-color: #8C86AA;");

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

        menu.getChildren().setAll(menuBar);
    }

    public void start(Stage primaryStage) {
        menu = new VBox();

        Thread audioThread = new Thread(new AudioThreadStart());
        audioThread.start();

        initializeMainMenu();

        mainLayout.setTop(menu);
        mainLayout.setCenter(polytechLabel);


        menu.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(polytechLabel, Pos.CENTER);

        Scene scene = new Scene(mainLayout, 800, 600);
        mainLayout.setStyle("-fx-background-color: black;");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showTask1UI() {
        Task1UI task1UI = new Task1UI(this);
        BorderPane task1VBox = task1UI.initialize();
        mainLayout.setCenter(task1VBox);
    }

    private void showTask2UI() {
        Task2UI task2UI = new Task2UI(this);
        BorderPane task2VBox = task2UI.initialize();
        mainLayout.setCenter(task2VBox);
    }

    private void showTask3UI() {
        Task3UI task3UI = new Task3UI(this);
        BorderPane task3VBox = task3UI.initialize();
        mainLayout.setCenter(task3VBox);
    }

    private void showTask4UI() {
        Task4UI task4UI = new Task4UI(this);
        BorderPane task4VBox = task4UI.initialize();
        mainLayout.setCenter(task4VBox);
    }

    private void showTask5UI() {
        Task5UI task5UI = new Task5UI(this);
        BorderPane task5VBox = task5UI.initialize();
        mainLayout.setCenter(task5VBox);
    }

    private void showTask6UI() {
        Task6UI task6UI = new Task6UI(this);
        BorderPane task6VBox = task6UI.initialize();
        mainLayout.setCenter(task6VBox);
    }

    public void showMainMenu() {
        initializeMainMenu();
        mainLayout.setCenter(polytechLabel);
    }

}

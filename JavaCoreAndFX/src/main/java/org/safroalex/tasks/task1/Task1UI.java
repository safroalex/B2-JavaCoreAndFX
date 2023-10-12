package org.safroalex.tasks.task1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.safroalex.tasks.task1.logic.*;
import javafx.scene.paint.Color;

import org.safroalex.ui.MainWindow;

public class Task1UI {
    MainWindow mainWindow;  // Ссылка на главное окно
    Hero hero;

    public Task1UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.hero = new Hero();
    }

    public BorderPane initialize() {
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15, 12, 15, 12));

        // Создаем текстовую область для вывода информации
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setMinHeight(100);
        outputArea.setMinWidth(200);
        outputArea.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        outputArea.setEditable(false);  // Защищаем от редактирования

        // Создаем выпадающий список для выбора стратегии
        ComboBox<String> strategyComboBox = new ComboBox<>();
        strategyComboBox.setMaxWidth(Double.MAX_VALUE);
        strategyComboBox.getItems().addAll("Angel", "Rocker", "SadMan");
        strategyComboBox.setStyle("-fx-font: 18px 'Serif';");

        // Создаем текстовое поле для ввода координат
        TextField coordinatesField = new TextField();
        coordinatesField.setMaxWidth(Double.MAX_VALUE);
        coordinatesField.setPromptText("Введите координаты в формате: x,y,z");
        coordinatesField.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));

        // Добавляем кнопку для выполнения перемещения
        Button moveButton = new Button("Переместиться");
        moveButton.setMaxWidth(Double.MAX_VALUE);
        moveButton.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        moveButton.setTextFill(Color.WHITE);
        moveButton.setStyle("-fx-background-color: #4CAF50;");
        moveButton.setOnAction(e -> {
            if (strategyComboBox.getValue() == null
                    || strategyComboBox.getValue().isEmpty()) {
                outputArea.appendText("Пожалуйста," +
                        " выберите тип перемещения.\n");
                return;
            }
            String[] coords = coordinatesField.getText().split(",");
            if (coords.length == 3) {
                try {
                    double x = Double.parseDouble(coords[0].trim());
                    double y = Double.parseDouble(coords[1].trim());
                    double z = Double.parseDouble(coords[2].trim());

                    Point newPoint = new Point(x, y, z);

                    hero.changeMovementType(strategyComboBox.getValue());

                    String moveResult = hero.move(newPoint);
                    outputArea.appendText(moveResult + "\n");
                    outputArea.appendText("Current position: "
                            + hero.getPosition().getCoordinatesMessage() + "\n");

                } catch (NumberFormatException ex) {
                    outputArea.appendText("Invalid input." +
                            " Please enter valid coordinates.\n");
                }
            } else {
                outputArea.appendText(
                        "Please enter all three coordinates (x, y, z).\n");
            }
        });

        // Кнопка для возврата в главное меню
        Button backButton = new Button("Вернуться в главное меню");
        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        backButton.setTextFill(Color.WHITE);
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction(e -> mainWindow.showMainMenu());
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        BorderPane.setMargin(backButton, new Insets(12, 12, 12, 12));

        // Добавляем все элементы в VBox
        vBox.getChildren().addAll(outputArea,
                strategyComboBox, coordinatesField, moveButton);

        // Устанавливаем VBox в центре и кнопку в нижнем левом углу
        borderPane.setCenter(vBox);
        borderPane.setBottom(backButton);

        return borderPane;
    }
}
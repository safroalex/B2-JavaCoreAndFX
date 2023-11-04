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

        // Создаем выпадающий список для выбора стратегии
        ComboBox<String> strategyComboBox = new ComboBox<>();
        strategyComboBox.getItems().addAll("Angel", "Rocker", "SadMan");
        strategyComboBox.setStyle("-fx-font: 18px 'Serif';");

        // Создаем текстовое поле для ввода координат
        TextField coordinatesField = new TextField();
        coordinatesField.setPromptText("Введите координаты в формате: x,y,z");


        // Добавляем кнопку для выполнения перемещения
        Button moveButton = new Button("Переместиться");
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
        backButton.setOnAction(e -> mainWindow.showMainMenu());
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        BorderPane.setMargin(backButton, new Insets(12, 12, 12, 12));

        // STYLES

        outputArea.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        outputArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        outputArea.setMinHeight(300);
        outputArea.setMinWidth(200);

        coordinatesField.setStyle("-fx-background-color: #C0C0C0;");

        moveButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        moveButton.setStyle("-fx-background-color: #00A676;");
        BorderPane.setAlignment(moveButton, Pos.CENTER);

        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        backButton.setStyle("-fx-background-color: #3C91E6;");
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

        // Добавляем все элементы в VBox
        vBox.getChildren().addAll(outputArea,
                strategyComboBox, coordinatesField, moveButton);

        // Устанавливаем VBox в центре и кнопку в нижнем левом углу
        borderPane.setCenter(vBox);
        borderPane.setBottom(backButton);

        return borderPane;
    }
}
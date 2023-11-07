package org.safroalex.tasks.task2;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.safroalex.tasks.task2.logic.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Method;

import org.safroalex.ui.MainWindow;
public class Task2UI {
    private TextArea outputArea;
    private TextArea outputArea2;
    private final MainWindow mainWindow;

    public Task2UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public BorderPane initialize() {

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(10);

        outputArea = new TextArea();
        outputArea.setEditable(false);

        outputArea2 = new TextArea();
        outputArea2.setEditable(false);

        Label label = new Label("Select number of repetitions:");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        ComboBox<String> repeatComboBox = new ComboBox<>();
        repeatComboBox.getItems().addAll("1", "2", "3", "4", "5");

        Button executeButton = new Button("Execute");
        executeButton.setOnAction(e -> {
            if (repeatComboBox.getValue() == null) {
                outputArea.appendText(
                        "Please select a number of repetitions.\n");
            } else {
                executeAnnotatedMethods(
                        Integer.parseInt(repeatComboBox.getValue()));
            }
        });

        Button executeButtonWithDefaults
                = new Button("Execute with Defaults in Area 2");
        executeButtonWithDefaults.setOnAction(e
                -> executeAnnotatedMethodsWithDefaults(
                        outputArea2, AntdClassForUIWithNumber.class));

        // Кнопка для возврата в главное меню
        Button backButton = new Button("Вернуться в главное меню");
        backButton.setOnAction(e -> mainWindow.showMainMenu());

        // STYLES

        outputArea.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        outputArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        outputArea.setMinHeight(300);
        outputArea.setMinWidth(200);

        outputArea2.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        outputArea2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        outputArea2.setMinHeight(300);
        outputArea2.setMinWidth(200);

        executeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        executeButton.setStyle("-fx-background-color: #00A676;");
        BorderPane.setAlignment(executeButton, Pos.BOTTOM_LEFT);

        executeButtonWithDefaults.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        executeButtonWithDefaults.setStyle("-fx-background-color: #00A676;");
        BorderPane.setAlignment(executeButtonWithDefaults, Pos.BOTTOM_LEFT);

        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        backButton.setStyle("-fx-background-color: #3C91E6;");
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

        vBox.setStyle("-fx-background-color: #000000;");

        vBox.getChildren().addAll(outputArea, label,
                repeatComboBox, executeButton, outputArea2,
                executeButtonWithDefaults, backButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);

        VBox scrollVbox = new VBox(scrollPane);

        borderPane.setCenter(scrollVbox);

        return borderPane;
    }

    private void executeAnnotatedMethods(int times) {
        AntdClassForUIWoutNumber obj = new AntdClassForUIWoutNumber();
        Method[] methods = AntdClassForUIWoutNumber.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                method.setAccessible(true);
                for (int i = 0; i < times; i++) {
                    try {
                        if (method.getModifiers() % 2 == 0) {
                            String output = switch (method.getName()) {
                                case "protectedMethodWithInt" -> (String) method.invoke(obj, 1);
                                case "protectedMethodWithDouble" -> (String) method.invoke(obj, 1.0);
                                case "privateMethodWithString" -> (String) method.invoke(obj, "test");
                                case "privateMethodWithClass" -> (String) method.invoke(obj, AntdClassForUIWoutNumber.class);
                                default -> "";
                            };

                            // Вывод в TextArea
                            outputArea.appendText(output + "\n");
                        }
                    } catch (Exception e) {
                        outputArea.appendText("An error occurred: " + e.getMessage() + "\n");
                    }
                }
            }
        }
    }

    private void executeAnnotatedMethodsWithDefaults(TextArea outputArea, Class<?> clazz) {
        Object obj;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            outputArea.appendText("Failed to create an instance of the class.");
            return;
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                Repeat repeatAnnotation = method.getAnnotation(Repeat.class);
                int times = repeatAnnotation.times();  // Получаем количество повторений из аннотации

                method.setAccessible(true);
                for (int i = 0; i < times; i++) {
                    try {
                        if (method.getModifiers() % 2 == 0) {
                            Object output;
                            // Вызов методов по условиям с использованием
                            // Reflection API для сравнения типов параметров
                            Class<?>[] paramTypes = method.getParameterTypes();
                            if (paramTypes.length > 0) {
                                if (paramTypes[0] == int.class) {
                                    output = method.invoke(obj, 1);
                                } else if (paramTypes[0] == double.class) {
                                    output = method.invoke(obj, 1.0);
                                } else if (paramTypes[0] == String.class) {
                                    output = method.invoke(obj, "test");
                                } else if (paramTypes[0] == Class.class) {
                                    output = method.invoke(obj, AntdClassForUIWithNumber.class);
                                } else {
                                    output = "Unsupported parameter type";
                                }
                            } else {
                                output = method.invoke(obj);  // Для методов без параметров
                            }
                            outputArea.appendText(output + "\n");
                        }
                    } catch (Exception e) {
                        outputArea.appendText("An error occurred: " + e.getMessage() + "\n");
                    }
                }
            }
        }
    }
}
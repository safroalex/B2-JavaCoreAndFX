package org.safroalex.tasks.task2;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.safroalex.tasks.task2.logic.*;
import java.lang.reflect.Method;

import org.safroalex.ui.MainWindow;
public class Task2UI {
    private TextArea outputArea;
    private TextArea outputArea2;
    private MainWindow mainWindow;

    public Task2UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public VBox initialize() {
        VBox vBox = new VBox(10);

        outputArea = new TextArea();
        outputArea.setEditable(false);

        outputArea2 = new TextArea();
        outputArea2.setEditable(false);

        ComboBox<String> repeatComboBox = new ComboBox<>();
        repeatComboBox.getItems().addAll("1", "2", "3", "4", "5");

        Button executeButton = new Button("Execute");
        executeButton.setOnAction(e -> {
            if (repeatComboBox.getValue() == null) {
                outputArea.appendText("Please select a number of repetitions.\n");
            } else {
                executeAnnotatedMethods(Integer.parseInt(repeatComboBox.getValue()));
            }
        });

        Button executeButtonWithDefaults = new Button("Execute with Defaults in Area 2");
        executeButtonWithDefaults.setOnAction(e -> executeAnnotatedMethodsWithDefaults(outputArea2, AntdClassForUIWithNumber.class));

        // Кнопка для возврата в главное меню
        Button backButton = new Button("Вернуться в главное меню");
        backButton.setOnAction(e -> mainWindow.showMainMenu());

        vBox.getChildren().addAll(outputArea, new Label("Select number of repetitions:"), repeatComboBox, executeButton, outputArea2, executeButtonWithDefaults, backButton);

        return vBox;
    }

    private void executeAnnotatedMethods(int times) {
        AntdClassForUIWoutNumber obj = new AntdClassForUIWoutNumber();
        Method[] methods = AntdClassForUIWoutNumber.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                method.setAccessible(true);
                for (int i = 0; i < times; i++) {
                    try {
                        String output = "";
                        // Вызов методов по условиям (примерно как в вашем Main)
                        if (method.getName().equals("protectedMethodWithInt")) {
                            output = (String) method.invoke(obj, 1);
                        } else if (method.getName().equals("protectedMethodWithDouble")) {
                            output = (String) method.invoke(obj, 1.0);
                        } else if (method.getName().equals("privateMethodWithString")) {
                            output = (String) method.invoke(obj, "test");
                        } else if (method.getName().equals("privateMethodWithClass")) {
                            output = (String) method.invoke(obj, AntdClassForUIWoutNumber.class);
                        }

                        // Вывод в TextArea
                        outputArea.appendText(output + "\n");

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
                        Object output;
                        // Вызов методов по условиям с использованием Reflection API для сравнения типов параметров
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
                    } catch (Exception e) {
                        outputArea.appendText("An error occurred: " + e.getMessage() + "\n");
                    }
                }
            }
        }
    }
}
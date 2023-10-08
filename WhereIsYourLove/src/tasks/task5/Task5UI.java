package tasks.task5;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import tasks.task5.Task5Logic.Methods;
import ui.MainWindow;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Task5UI {
    private TextArea outputArea;

    private final MainWindow mainWindow;

    public Task5UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public VBox initialize() {
        VBox vBox = new VBox(10);

        // Стилизация
        vBox.setStyle("-fx-background-color: #f4f4f4;");
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-size: 14px;");

        TextField inputField1 = new TextField();
        Button executeButton1 = new Button("Execute Method 1");
        executeButton1.setOnAction(e -> {
            try {
                List<Integer> list = Arrays.asList(Arrays.stream(inputField1.getText().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
                if (list.isEmpty()) {
                    outputArea.appendText("Error: Input for Method 1 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 1: " + Methods.method1(list) + "\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error in Method 1: Please enter valid integers separated by commas.\n");
            }
        });

        TextField inputField2 = new TextField();
        Button executeButton2 = new Button("Execute Method 2");
        executeButton2.setOnAction(e -> {
            try {
                List<String> list = Arrays.asList(inputField2.getText().split(","));
                if (list.isEmpty() || list.contains("")) {
                    outputArea.appendText("Error: Input for Method 2 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 2: " + Methods.method2(list) + "\n");
            } catch (Exception ex) {
                outputArea.appendText("Error in Method 2: " + ex.getMessage() + "\n");
            }
        });

        TextField inputField3 = new TextField();
        Button executeButton3 = new Button("Execute Method 3");
        executeButton3.setOnAction(e -> {
            try {
                List<Integer> list = Arrays.asList(Arrays.stream(inputField3.getText().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
                if (list.isEmpty()) {
                    outputArea.appendText("Error: Input for Method 3 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 3: " + Methods.method3(list) + "\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error in Method 3: Please enter valid integers separated by commas.\n");
            }
        });

        TextField inputField4 = new TextField();
        TextField inputField4Letter = new TextField();
        Button executeButton4 = new Button("Execute Method 4");
        executeButton4.setOnAction(e -> {
            try {
                List<String> list = Arrays.stream(inputField4.getText().split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
                String letter = inputField4Letter.getText().trim();
                if (list.isEmpty() || letter.isEmpty()) {
                    outputArea.appendText("Error: Input for Method 4 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 4: " + Methods.method4(list, letter) + "\n");
            } catch (Exception ex) {
                outputArea.appendText("Error in Method 4: " + ex.getMessage() + "\n");
            }
        });

        TextField inputField5 = new TextField();
        Button executeButton5 = new Button("Execute Method 5");
        executeButton5.setOnAction(e -> {
            try {
                List<Integer> list = Arrays.asList(Arrays.stream(inputField5.getText().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
                if (list.isEmpty()) {
                    outputArea.appendText("Error: Input for Method 5 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 5: " + Methods.method5(list) + "\n");
            } catch (NoSuchElementException | NumberFormatException ex) {
                outputArea.appendText("Exception in Method 5: " + ex.getMessage() + "\n");
            }
        });

        TextField inputField6 = new TextField();
        Button executeButton6 = new Button("Execute Method 6");
        executeButton6.setOnAction(e -> {
            try {
                int[] array = Arrays.stream(inputField6.getText().split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if (array.length == 0) {
                    outputArea.appendText("Error: Input for Method 6 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 6: " + Methods.method6(array) + "\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error in Method 6: Please enter valid integers separated by commas.\n");
            }
        });

        TextField inputField7 = new TextField();
        Button executeButton7 = new Button("Execute Method 7");
        executeButton7.setOnAction(e -> {
            try {
                List<String> list = Arrays.asList(Arrays.stream(inputField7.getText().split(","))
                        .map(String::trim)
                        .toArray(String[]::new));
                if (list.isEmpty() || list.contains("")) {
                    outputArea.appendText("Error: Input for Method 7 should not be empty.\n");
                    return;
                }
                outputArea.appendText("Result of Method 7: " + Methods.method7(list) + "\n");
            } catch (Exception ex) {
                outputArea.appendText("Error in Method 7: " + ex.getMessage() + "\n");
            }
        });


        // Кнопка для возврата в главное меню
        Button backButton = new Button("Вернуться в главное меню");
        backButton.setOnAction(e -> mainWindow.showMainMenu());

        // Добавление тултипов
        Tooltip tooltip1 = new Tooltip("Введите целые числа, разделенные запятыми");
        Tooltip tooltip2 = new Tooltip("Введите строки, разделенные запятыми");
        Tooltip tooltip3 = new Tooltip("Введите целые числа, разделенные запятыми");
        Tooltip tooltip4 = new Tooltip("Введите строки, разделенные запятыми");
        Tooltip tooltip5 = new Tooltip("Введите целые числа, разделенные запятыми");
        Tooltip tooltip6 = new Tooltip("Введите целые числа, разделенные запятыми");
        Tooltip tooltip7 = new Tooltip("Введите строки, разделенные запятыми");

        Tooltip.install(inputField1, tooltip1);
        Tooltip.install(inputField2, tooltip2);
        Tooltip.install(inputField3, tooltip3);
        Tooltip.install(inputField4, tooltip4);
        Tooltip.install(inputField5, tooltip5);
        Tooltip.install(inputField6, tooltip6);
        Tooltip.install(inputField7, tooltip7);

        // Добавление разделителей
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();
        Separator separator4 = new Separator();
        Separator separator5 = new Separator();
        Separator separator6 = new Separator();
        Separator separator7 = new Separator();

        // Создание контейнеров для каждой пары поля-кнопка
        VBox container1 = new VBox(inputField1, executeButton1);
        VBox container2 = new VBox(inputField2, executeButton2);
        VBox container3 = new VBox(inputField3, executeButton3);
        VBox container4 = new VBox(inputField4, inputField4Letter, executeButton4);
        VBox container5 = new VBox(inputField5, executeButton5);
        VBox container6 = new VBox(inputField6, executeButton6);
        VBox container7 = new VBox(inputField7, executeButton7);

        // Установка пространства между элементами в контейнерах
        container1.setSpacing(10);
        container2.setSpacing(10);
        container3.setSpacing(10);
        container4.setSpacing(10);
        container5.setSpacing(10);
        container6.setSpacing(10);
        container7.setSpacing(10);

        // Добавление контейнеров и сепараторов в основной VBox
        vBox.getChildren().addAll(
                new Label("Input for Method 1 (integers):"), container1, separator1,
                new Label("Input for Method 2 (strings):"), container2, separator2,
                new Label("Input for Method 3 (integers):"), container3, separator3,
                new Label("Input for Method 4 (strings):"), container4, separator4,
                new Label("Input for Method 5 (integers):"), container5, separator5,
                new Label("Input for Method 6 (integers):"), container6, separator6,
                new Label("Input for Method 7 (strings):"), container7, separator7, backButton,
                outputArea
        );

        // Создание ScrollPane и добавление вашего VBox в него
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);  // Чтобы ширина VBox подстраивалась под ширину ScrollPane

        // Создаем новый VBox, чтобы вернуть его как корневой элемент с прокруткой
        VBox root = new VBox(scrollPane);  // Здесь можно добавить еще элементы, если нужно

        return root;
    }
}
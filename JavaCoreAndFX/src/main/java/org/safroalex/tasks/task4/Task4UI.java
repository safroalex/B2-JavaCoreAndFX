package org.safroalex.tasks.task4;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.safroalex.tasks.task4.logic.TextTranslator;
import org.safroalex.tasks.task4.logic.Utils;
import org.safroalex.tasks.task4.logic.exceptions.InvalidFileFormatException;
import org.safroalex.ui.MainWindow;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Task4UI {
    private final Map<String, String> dictionary = new HashMap<>();
    private final MainWindow mainWindow;

    public Task4UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public BorderPane initialize() {
        VBox task4VBox = new VBox();

        BorderPane borderPane = new BorderPane();
        // TextArea для Drag-and-Drop словаря
        TextArea dictionaryArea = new TextArea();
        dictionaryArea.setPromptText("Перетащите сюда файл со словарем...");

        dictionaryArea.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });

        dictionaryArea.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasFiles()) {
                try {
                    File file = db.getFiles().get(0);
                    List<String> lines = Files.readAllLines(file.toPath());

                    for (String line : lines) {
                        String[] parts = line.split("=");
                        if (parts.length == 2) {
                            dictionary.put(parts[0].trim(), parts[1].trim());
                            System.out.println("Added to dictionary: "
                                    + parts[0].trim()
                                    + " = " + parts[1].trim());
                        }
                    }

                    success = true;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            event.setDropCompleted(success);
        });

        TextArea translatedTextArea = new TextArea();
        translatedTextArea.setEditable(false);

        // Добавляем новый TextArea для ручного ввода словаря
        TextArea manualDictionaryArea = new TextArea();
        manualDictionaryArea.setPromptText
                        ("Введите словарь вручную" +
                                " (каждая пара на новой строке)");

        // Кнопка для загрузки ручного словаря
        Button loadManualDictionaryButton
                = new Button("Загрузить ручной словарь");
        loadManualDictionaryButton.setOnAction(e -> {
            String checkManualDictionaryText = manualDictionaryArea.getText();
            if (checkManualDictionaryText.isEmpty()) {
                translatedTextArea.setText("Поле для ручного ввода" +
                        " словаря пусто. Загрузка не выполнена.");
                return;
            }
            try {
                Map<String, String> manualDictionary
                        = Utils
                        .parseManualDictionary(manualDictionaryArea.getText());
                dictionary.putAll(manualDictionary);
                translatedTextArea.setText("Manual dictionary loaded successfully.");
            } catch (InvalidFileFormatException ex) {
                ex.printStackTrace();
                System.out.println("Failed to load manual dictionary:" +
                        " Invalid format.");
            }
        });

        TextArea englishTextArea = new TextArea();
        englishTextArea.setPromptText("Введи текст для перевода.");
        Button translateButton = new Button("Перевести");

        translateButton.setOnAction(e -> {
            if (dictionary.isEmpty()) {
                translatedTextArea.setText("Словарь пуст." +
                        " Пожалуйста, загрузите словарь.");
                return;
            }
            String inputText = englishTextArea.getText();
            String translatedText
                    = TextTranslator.translateText(dictionary, inputText);
            translatedTextArea.setText(translatedText);
        });

        // Кнопка для возврата в главное меню
        Button backButton = new Button("Вернуться в главное меню");
        backButton.setOnAction(e -> mainWindow.showMainMenu());

        VBox container1 = new VBox(manualDictionaryArea, loadManualDictionaryButton, dictionaryArea, englishTextArea);
        VBox container2 = new VBox(translateButton, backButton, translatedTextArea);

        container1.setSpacing(20);
        container2.setSpacing(20);

        // STYLES

        manualDictionaryArea.setStyle("-fx-control-inner-background: #C0C0C0;");
        manualDictionaryArea.setMaxHeight(100);
        manualDictionaryArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        dictionaryArea.setMaxHeight(100);
        dictionaryArea.setStyle("-fx-control-inner-background: #C0C0C0;");
        dictionaryArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        englishTextArea.setMaxHeight(100);
        englishTextArea.setStyle("-fx-control-inner-background: #C0C0C0;");
        englishTextArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        translatedTextArea.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        translatedTextArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        loadManualDictionaryButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        loadManualDictionaryButton.setStyle("-fx-background-color: #00A676;");

        translateButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        translateButton.setStyle("-fx-background-color: #00A676;");

        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        backButton.setStyle("-fx-background-color: #3C91E6;");

        task4VBox.setStyle("-fx-background-color: #000000;");

        task4VBox.getChildren().addAll(container1, container2);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(task4VBox);
        scrollPane.setFitToWidth(true);

        VBox scrollVBox= new VBox(scrollPane);

        borderPane.setCenter(scrollVBox);

        return borderPane;
    }
}

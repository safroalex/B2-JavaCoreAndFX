package org.safroalex.tasks.task3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.safroalex.ui.MainWindow;
import org.safroalex.tasks.task3.logic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Task3UI {
    private MainWindow mainWindow;

    public Task3UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public BorderPane initialize() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15,12,15,12));
        vBox.setSpacing(20);

        BorderPane borderPane = new BorderPane();

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        Button runButton = new Button("Запустить алгоритм");
        runButton.setOnAction(e -> {
            // SegregateMlecopit
            Collection<Hierarchy.Mlecopit> mlecopit = Arrays.asList(
                    new Hierarchy.Coshki(),
                    new Hierarchy.Ezhovie(),
                    new Hierarchy.EzhStandart(),
                    new Hierarchy.Manul(),
                    new Hierarchy.Ryci()
            );

            Collection<Hierarchy.Ezhovie> mlezhovie = new ArrayList<>();
            Collection<Hierarchy.Coshki> mlcoshki = new ArrayList<>();
            Collection<Hierarchy.Predator> mlpredator = new ArrayList<>();

            Segregate mlsegregate = new Segregate();
            mlsegregate.segregate(mlecopit, mlezhovie, mlcoshki, mlpredator);

            outputArea.setText("Mlezhovie size: " + mlezhovie.size());
            outputArea.appendText("\n" + "Mlcoshki size: " + mlcoshki.size());
            outputArea.appendText("\n" + "Mlpredator size: " + mlpredator.size());


            //SegregatePredator
            Collection<Hierarchy.Predator> predator = Arrays.asList(
                    new Hierarchy.Ryci(),
                    new Hierarchy.Manul(),
                    new Hierarchy.Ryci()
            );

            Collection<Hierarchy.Chordate> prchordate = new ArrayList<>();
            Collection<Hierarchy.Manul> prmanul = new ArrayList<>();
            Collection<Hierarchy.Coshki> prcoshki = new ArrayList<>();

            Segregate prsegregate = new Segregate();
            prsegregate.segregate(predator, prchordate, prmanul, prcoshki);

            outputArea.appendText("\n" + "Prchordate size: " + prchordate.size());
            outputArea.appendText("\n" + "Prmanul size: " + prmanul.size());
            outputArea.appendText("\n" + "Prcoshki size: " + prcoshki.size());


            //SegregateEzhovie
            Collection<Hierarchy.Ezhovie> ezhovie = Arrays.asList(
                    new Hierarchy.Ezhovie(),
                    new Hierarchy.EzhStandart(),
                    new Hierarchy.EzhStandart()
            );

            Collection<Hierarchy.Nasecomoyadnye> eznasecomoyadnye
                    = new ArrayList<>();
            Collection<Hierarchy.Predator> ezpredator = new ArrayList<>();
            Collection<Hierarchy.Predator> ezpredator2 = new ArrayList<>();

            Segregate ezsegregate = new Segregate();
            ezsegregate
                    .segregate(ezhovie, eznasecomoyadnye, ezpredator, ezpredator2);

            outputArea.appendText("\n" + "Eznasecomoyadnye size: " + eznasecomoyadnye.size());
            outputArea.appendText("\n" + "Ezpredator size: " + ezpredator.size());
            outputArea.appendText("\n" + "Ezpredator2 size: " + ezpredator2.size());
        });

        Button backButton = new Button("Вернуться в главное меню");
        backButton.setOnAction(e -> mainWindow.showMainMenu());


        // STYLES
        runButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        runButton.setStyle("-fx-background-color: #00A676;");
        BorderPane.setAlignment(runButton, Pos.CENTER);

        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        backButton.setStyle("-fx-background-color: #3C91E6;");
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

        outputArea.setStyle("-fx-control-inner-background:#000;"  +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");
        outputArea.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        outputArea.setMinHeight(300);
        outputArea.setMinWidth(200);

        vBox.getChildren().addAll(outputArea, runButton, backButton);

        borderPane.setCenter(vBox);
        borderPane.setBottom(backButton);

        return borderPane;
    }
}

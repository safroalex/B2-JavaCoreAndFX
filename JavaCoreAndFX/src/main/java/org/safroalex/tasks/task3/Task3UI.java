package org.safroalex.tasks.task3;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
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

    public VBox initialize() {
        VBox vBox = new VBox();

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

        outputArea.setStyle("-fx-control-inner-background:#000;" +
                " -fx-font-family: Consolas; -fx-highlight-fill: dodgerblue;" +
                " -fx-highlight-text-fill: white; -fx-text-fill: white;");

        vBox.getChildren().addAll(outputArea, runButton, backButton);

        return vBox;
    }
}

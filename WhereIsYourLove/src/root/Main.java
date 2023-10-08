package root;
// В классе Main
import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainWindow;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Тут можно инициализировать ваше приложение, например, открыть MainWindow
        new MainWindow().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

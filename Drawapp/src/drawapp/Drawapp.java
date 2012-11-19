package drawapp;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class Drawapp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainWindow mainWindow = new MainWindow(primaryStage);
        ImagePanel imagePanel = mainWindow.getImagePanel();
        primaryStage.show();
    }
}

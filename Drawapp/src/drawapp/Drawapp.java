package drawapp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
    public void start(Stage primaryStage) throws IOException {
        MainWindow mainWindow = new MainWindow(primaryStage);
        ImagePanel imagePanel = mainWindow.getImagePanel();
        Reader reader = new InputStreamReader(System.in);
        Parser parser = new Parser(reader,imagePanel,mainWindow);
        parser.parseWithButtons(mainWindow.getNext(),mainWindow.getComplete());
        primaryStage.show();
    }
}

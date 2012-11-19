package drawapp;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Drawapp extends Application
{
  public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Draw App");
        Group root = new Group();
        Scene scene = new Scene(root, 640, 480);
    
    primaryStage.setScene(scene);

    primaryStage.show();
}
}

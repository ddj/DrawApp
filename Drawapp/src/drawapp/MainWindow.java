package drawapp;

import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class MainWindow {

    public static final int defaultWidth = 800;
    public static final int defaultHeight = 600;
    Scene scene;
    private Stage primaryStage;
    ImagePanel gridPicture;
    ImagePanel gridButtons = new ImagePanel(800, 50);
    private TextArea gridText = new TextArea();
    private Button buttonClose = new Button("CloseWindow");
    Button buttonNext = new Button("Next");
    Button buttonComplete = new Button("Complete");

    public MainWindow(Stage stage) {
        this(stage, defaultWidth, defaultHeight);
    }

    public MainWindow(Stage primaryStage, int width, int height) {
        primaryStage.setTitle("Draw App");
        this.primaryStage = primaryStage;
        Group root = new Group();
        scene = new Scene(root, defaultWidth, defaultHeight);
        GridPane gridpane = buildGUI();
        root.getChildren().add(gridpane);
        primaryStage.setScene(scene);
    }

    private GridPane buildGUI() {
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(0);

        gridPicture = new ImagePanel(800, 400);
        gridpane.add(gridPicture, 0, 0);

        gridText.setWrapText(true);
        gridText.setPrefWidth(800);
        gridText.setPrefHeight(150);
        GridPane.setHalignment(gridText, HPos.CENTER);
        gridpane.add(gridText, 0, 1);
        postMessage("Drawing Complete.");

        gridButtons.setAlignment(Pos.CENTER);
        gridButtons.setBackgroundColour("#E8E8E8");
        buttonClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        Button buttonSave = new Button("Save");
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WritableImage wim = new WritableImage(defaultHeight,defaultWidth );
                gridPicture.snapshot(null, wim);          
                File file = new File("Image.png");
                 try {
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
                 } catch (Exception s) 
                 {
                }
            }
        });
        gridButtons.add(buttonComplete);
        gridButtons.add(buttonNext);
        gridButtons.add(buttonClose);
        gridButtons.add(buttonSave);
        gridpane.add(gridButtons, 0, 2);

        return gridpane;
    }

    public Button getNext() {
        return buttonNext;
    }

    public Button getComplete() {
        return buttonComplete;
    }

    public ImagePanel getImagePanel() {
        return gridPicture;
    }

    public void postMessage(final String s) {
        gridText.setText(s);
    }

    public Stage getStage() {
        return primaryStage;
    }
    
    public void changeSize(int width,int height){
      gridPicture.setPrefHeight(height-200);
      gridPicture.setPrefWidth(width);
      gridText.setPrefWidth(width);
      gridButtons.setPrefWidth(width);
  }
}
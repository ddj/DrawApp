package drawapp;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author David
 */
public class ImagePanel extends HBox {

    private Paint stringColour = Color.BLACK;
    private Group picture = new Group();

    public ImagePanel(int width, int height) {
        setImageSize(width, height);
    }

    private void setImageSize(int width, int height) {
        this.add(picture);
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        ImagePanel.setHgrow(picture, Priority.NEVER);
        this.setMaxHeight(height);
        this.setMaxWidth(width);
    }

    public void add(Node g) {
        this.getChildren().add(g);
    }

    public void setBackgroundColour(String st) {
        picture.setStyle("-fx-fill: " + st + ";");
    }

    public void clear(Color colour) {
        setBackgroundColour("00000000");
    }

    public void setGradient(Color start, Color finish) {
        Stop[] stops = new Stop[]{new Stop(0, start), new Stop(1, finish)};
        stringColour = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
    }

    public void setColour(Color colour) {
        stringColour = colour;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStroke(stringColour);
        picture.getChildren().add(line);
        stringColour = Color.BLACK;
    }

    public void drawRect(int x1, int y1, int x2, int y2) {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rect.setStroke(Paint.valueOf("000000"));
        rect.setFill(Paint.valueOf("00000000"));
        picture.getChildren().add(rect);
    }

    public void fillRect(int x1, int y1, double x2, double y2) {
        Rectangle rectFill = new Rectangle(x1, y1, x2, y2);
        rectFill.setFill(stringColour);
        picture.getChildren().add(rectFill);
        stringColour = Color.BLACK;
    }
    
    public void drawRoundRect(int x1, int y1, int x2, int y2, int r) {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rect.setArcWidth(r);
        rect.setArcHeight(r);
        rect.setStroke(Paint.valueOf("000000"));
        rect.setFill(Paint.valueOf("00000000"));
        picture.getChildren().add(rect);
    }

    public void fillRoundRect(int x1, int y1, int x2, int y2, int r) {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rect.setArcWidth(r);
        rect.setArcHeight(r);
        rect.setFill(stringColour);
        picture.getChildren().add(rect);
        stringColour = Color.BLACK;
    }

    public void drawString(int x, int y, String s) {
        Text t = new Text(x, y, s);
        picture.getChildren().add(t);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        Arc arc = new Arc(x, y, width / 2, height / 2, startAngle, arcAngle);
        arc.setStroke(stringColour);
        arc.setFill(Paint.valueOf("00000000"));
        picture.getChildren().add(arc);
        stringColour = Color.BLACK;
    }

    public void drawOval(int x, int y, int width, int height) {
        Ellipse oval = new Ellipse(x, y, width, height);
        oval.setStroke(stringColour);
        oval.setFill(Paint.valueOf("00000000"));
        picture.getChildren().add(oval);
        stringColour = Color.BLACK;
    }
    
    public void drawImage(int x, int y, int width, int height, String name) {

        Image image4 = new Image("file:" + name, width, height, false, false);
        ImageView imgView = new ImageView(image4);
        picture.getChildren().add(imgView);
        picture.setLayoutX(x);
        picture.setLayoutY(y);
    }
}

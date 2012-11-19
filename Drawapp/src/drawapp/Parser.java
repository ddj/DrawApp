package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Parser {

    private BufferedReader reader;
    private ImagePanel picture;
    private MainWindow frame;
    private int i = 0;

    public Parser(Reader reader, ImagePanel picture, MainWindow frame) {
        this.reader = new BufferedReader(reader);
        this.picture = picture;
        this.frame = frame;
    }

    public void parse() {
        try {
            String line = reader.readLine();
            while (line != null) {
                parseLine(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            frame.postMessage("Parse failed.");
            return;
        } catch (ParseException e) {
            frame.postMessage("Parse Exception: " + e.getMessage());
            return;
        }
        frame.postMessage("Drawing complete.");
    }

    private void parseLine(String line) throws ParseException {
        if (line.length() < 2) {
            return;
        }
        String command = line.substring(0, 2);
        if (command.equals("DL")) {
            drawLine(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DR")) {
            drawRect(line.substring(2, line.length()));
            return;
        }
        if (command.equals("FR")) {
            fillRect(line.substring(2, line.length()));
            return;
        }
        if (command.equals("SC")) {
            setColour(line.substring(3, line.length()));
            return;
        }
        if (command.equals("DS")) {
            drawString(line.substring(3, line.length()));
            return;
        }
        if (command.equals("DA")) {
            drawArc(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DO")) {
            drawOval(line.substring(2, line.length()));
            return;
        }
        if (command.equals("SG")) {
            setGradient(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DI")) {
            drawImage(line.substring(3, line.length()));
            return;
        }
        if (command.equals("RR")) {
            drawRoundRect(line.substring(2, line.length()));
            return;
        }
        if (command.equals("RF")) {
            fillRoundRect(line.substring(2, line.length()));
            return;
        }
        throw new ParseException("Unknown drawing command");
    }

    private void drawRoundRect(String args) throws ParseException {
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        int r = -1;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        r = getInteger(tokenizer);
        if ((x1 < 0) || (y1 < 0) || (x2 < 0) || (y2 < 0)) {
            throw new ParseException("Invalid values for Rectangle command");
        }
        picture.drawRoundRect(x1, y1, x2, y2, r);
    }

    private void fillRoundRect(String args) throws ParseException {
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        int r = -1;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        r = getInteger(tokenizer);
        if ((x1 < 0) || (y1 < 0) || (x2 < 0) || (y2 < 0)) {
            throw new ParseException("Invalid values for Rectangle command");
        }
        picture.fillRoundRect(x1, y1, x2, y2, r);
    }

    private void drawImage(String args) throws ParseException {
        int x = -1;
        int y = -1;
        int width = -1;
        int height = -1;
        String s = "";
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        if ((x < 0) || (y < 0)) {
            throw new ParseException("Invalid values for Draw Image coommand");
        }
        int position = args.indexOf("@");
        if (position == -1) {
            throw new ParseException("DrawImage image path is missing");
        }
        s = args.substring(position + 1, args.length());
        System.out.println(s);
        picture.drawImage(x, y, width, height, s);
    }

    private void drawLine(String args) throws ParseException {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        picture.drawLine(x1, y1, x2, y2);
    }

    private void drawRect(String args) throws ParseException {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        picture.drawRect(x1, y1, x2, y2);
    }

    private void fillRect(String args) throws ParseException {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        picture.fillRect(x1, y1, x2, y2);
    }

    private void drawArc(String args) throws ParseException {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int startAngle = 0;
        int arcAngle = 0;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        startAngle = getInteger(tokenizer);
        arcAngle = getInteger(tokenizer);
        picture.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    private void drawOval(String args) throws ParseException {
        int x1 = 0;
        int y1 = 0;
        int width = 0;
        int height = 0;

        StringTokenizer tokenizer = new StringTokenizer(args);
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        picture.drawOval(x1, y1, width, height);
    }

    private void drawString(String args) throws ParseException {
        int x = 0;
        int y = 0;
        String s = "";
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        int position = args.indexOf("@");
        if (position == -1) {
            throw new ParseException("DrawString string is missing");
        }
        s = args.substring(position + 1, args.length());
        picture.drawString(x, y, s);
    }

    private void setGradient(String args) throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(args);
        String S = getString(tokenizer);
        String E = getString(tokenizer);
        Color start = getColour(S);
        Color end = getColour(E);
        picture.setGradient(start, end);
        System.out.println(S + E);
    }

    private Color getColour(String colourName) throws ParseException {
        if (colourName.equals("black")) {
            return (Color.BLACK);
        }
        if (colourName.equals("blue")) {
            return (Color.BLUE);
        }
        if (colourName.equals("cyan")) {
            return (Color.CYAN);
        }
        if (colourName.equals("darkgray")) {
            return (Color.DARKGRAY);
        }
        if (colourName.equals("gray")) {
            return (Color.GRAY);
        }
        if (colourName.equals("green")) {
            return (Color.GREEN);
        }
        if (colourName.equals("lightgray")) {
            return (Color.LIGHTGRAY);
        }
        if (colourName.equals("magenta")) {
            return (Color.MAGENTA);
        }
        if (colourName.equals("orange")) {
            return (Color.ORANGE);
        }
        if (colourName.equals("pink")) {
            return (Color.PINK);
        }
        if (colourName.equals("red")) {
            return (Color.RED);
        }
        if (colourName.equals("white")) {
            return (Color.WHITE);
        }
        if (colourName.equals("yellow")) {
            return (Color.YELLOW);
        }
        throw new ParseException("Invalid colour name");
    }

    private void setColour(String colourName) throws ParseException {
        if (colourName.equals("black")) {
            picture.setColour(Color.BLACK);
            return;
        }
        if (colourName.equals("blue")) {
            picture.setColour(Color.BLUE);
            return;
        }
        if (colourName.equals("cyan")) {
            picture.setColour(Color.CYAN);
            return;
        }
        if (colourName.equals("darkgray")) {
            picture.setColour(Color.DARKGRAY);
            return;
        }
        if (colourName.equals("gray")) {
            picture.setColour(Color.GRAY);
            return;
        }
        if (colourName.equals("green")) {
            picture.setColour(Color.GREEN);
            return;
        }
        if (colourName.equals("lightgray")) {
            picture.setColour(Color.LIGHTGRAY);
            return;
        }
        if (colourName.equals("magenta")) {
            picture.setColour(Color.MAGENTA);
            return;
        }
        if (colourName.equals("orange")) {
            picture.setColour(Color.ORANGE);
            return;
        }
        if (colourName.equals("pink")) {
            picture.setColour(Color.PINK);
            return;
        }
        if (colourName.equals("red")) {
            picture.setColour(Color.RED);
            return;
        }
        if (colourName.equals("white")) {
            picture.setColour(Color.WHITE);
            return;
        }
        if (colourName.equals("yellow")) {
            picture.setColour(Color.YELLOW);
            return;
        }
        throw new ParseException("Invalid colour name");
    }

    private int getInteger(StringTokenizer tokenizer) throws ParseException {
        if (tokenizer.hasMoreTokens()) {
            return Integer.parseInt(tokenizer.nextToken());
        } else {
            throw new ParseException("Missing Integer value");
        }
    }

    private String getString(StringTokenizer tokenizer) throws ParseException {
        if (tokenizer.hasMoreTokens()) {
            return tokenizer.nextToken();
        } else {
            throw new ParseException("Missing String value");
        }
    }

    public void parseWithButtons(final Button b, final Button complete) throws IOException {
        frame.postMessage("Press Next to draw image step by step or Complete to draw the complete image");
        String line = reader.readLine();
        final ArrayList<String> asl = new ArrayList<String>();
        while (line != null) {
            asl.add(line);
            line = reader.readLine();
        }
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    frame.postMessage("Press Next to continue to draw image");
                    parseLine(asl.get(i));
                    i++;
                    if (i == asl.size()) {
                        frame.postMessage("Drawing complete.");
                        b.setDisable(true);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        complete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    for (String line : asl) {
                        parseLine(line);
                    }
                    frame.postMessage("Drawing complete.");
                    b.setDisable(true);
                    complete.setDisable(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}

package Games.Checkers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Checker {

    private String name;
    private int color;
    boolean isQueen;

    final static int EMPTY = 0;
    final static int WHITE = 1;
    final static int BLACK = 2;

    private static final int XSIZE = 90;
    private static final int YSIZE = 90;
    private double x = 0;
    private double y = 0;
    private double dx = 0;
    private double dy = 0;


    /*

    boolean goldWay;
    boolean doubleWayG1A7;
    boolean doubleWayH2B8;
    boolean tripleWayC1A3;
    boolean tripleWayC1H6;
    boolean tripleWayH6F8;
    boolean tripleWayA3F8;
    boolean ultraWayA5D8;
    boolean ultraWayH4D8;
    boolean ultraWayE1A5;
    boolean ultraWayE1H4;
    */
    public Checker(String name, int color) {

        this.name = name;
        this.color = color;
    }

    public void addImageOnScreen(Graphics g) {

        BufferedImage img = null;

        if (color == WHITE) {
            try {
                img = ImageIO.read(new File("src\\Games\\Checkers\\WhiteCheck.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (color == BLACK) {
            try {
                img = ImageIO.read(new File("src\\Games\\Checkers\\BlackCheck.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(img,0, 0, null);
    }
}

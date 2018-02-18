package Games.Checkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    BufferedImage img;

    GamePanel() {
        setLayout(new FlowLayout());
        CheckersRules.newGame(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            img = ImageIO.read(new File("src\\Games\\Checkers\\ChessBoard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, this);
        CheckersRules.whiteChecker.get(0).addImageOnScreen(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(697, 720);
    }
}

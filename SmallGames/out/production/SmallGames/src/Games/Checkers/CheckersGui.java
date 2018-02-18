package Games.Checkers;

import javax.swing.*;
import java.awt.*;

public class CheckersGui extends JFrame {

    //For test
    public static void main(String[] args) {
        CheckersGui gui = new CheckersGui();
    }

    public CheckersGui() {
        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}

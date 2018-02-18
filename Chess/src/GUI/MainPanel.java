package GUI;

import javax.swing.*;

public class MainPanel extends JPanel {

    public MainPanel() {

        try {
            UIManager.setLookAndFeel("WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(800, 800);
        
    }
}

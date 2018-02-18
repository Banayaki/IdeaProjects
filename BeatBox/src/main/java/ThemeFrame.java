package main.java;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThemeFrame extends JFrame {

    private JPanel buttonPanel;
    public ThemeFrame(JFrame frame){
        buttonPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            makeButton(info.getName(), info.getClassName(), frame);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                setVisible(false);
            }
        });
        add(buttonPanel);
        pack();
        setTitle("Themes");
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void makeButton(String name, final String plafName, JFrame frame){
        JButton button = new JButton(name);
        buttonPanel.add(button);

        button.addActionListener(e -> {
            try{
                UIManager.setLookAndFeel(plafName);
                SwingUtilities.updateComponentTreeUI(frame);
                SwingUtilities.updateComponentTreeUI(ThemeFrame.this);
                pack();
            } catch (Exception ex) { ex.printStackTrace(); }
        });
    }
}

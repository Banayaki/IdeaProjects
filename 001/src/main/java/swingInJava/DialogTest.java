package main.java.swingInJava;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogTest extends JFrame {
    private static final int DEFAULT_WIDTH   = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;

    public static void main(String[] args) {
        DialogTest trs = new DialogTest();
    }

    public DialogTest(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialog == null) {
                    dialog = new AboutDialog(DialogTest.this);
                    dialog.setVisible(true);
                }
            }
        });
        fileMenu.add(aboutItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package Gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryGui extends JFrame {

    public static void main(String[] args) {
        EntryGui gui = new EntryGui();
    }

    public EntryGui() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Entry");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2, 4,4));
        panel.setBorder(new EmptyBorder(15,15,15,15));

        JLabel userName = new JLabel("Username: ", SwingConstants.CENTER);
        panel.add(userName);
        JTextField nameField = new JTextField(20);
        panel.add(nameField);
        JLabel passName = new JLabel("Password: ", SwingConstants.CENTER);
        panel.add(passName);
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(passwordField);

        Service.AllForGui.makeButton(panel, "Sign in",'s', new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });

        Service.AllForGui.makeButton(panel, "Login", 'l' ,  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameListGui gameListGui = new GameListGui();
                    }
                });
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}

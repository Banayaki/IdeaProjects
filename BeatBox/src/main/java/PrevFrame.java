package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PrevFrame extends JFrame {
    private JTextField nameField;
    private JLabel label;
    private JButton okButton;

    PrevFrame(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("options.txt"));
            UIManager.setLookAndFeel(reader.readLine());
            reader.close();
        } catch (Exception ex) {ex.printStackTrace();}

        JPanel panel = new JPanel();

        JRadioButton lanModeOn = new JRadioButton("LAN On", true);
        JRadioButton lanModeOff = new JRadioButton("LAN Off");
        JPanel radioButtonPanel = new JPanel();
        ButtonGroup lanMode = new ButtonGroup();
        lanMode.add(lanModeOn);
        lanMode.add(lanModeOff);
        radioButtonPanel.setLayout(new FlowLayout());
        radioButtonPanel.add(lanModeOn);
        radioButtonPanel.add(lanModeOff);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label = new JLabel("Your name: ");
        setTitle("Write your name");
        nameField = new JTextField(20);
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BeatBoxClient client = new BeatBoxClient();
                client.startUp(nameField.getText(), lanModeOn.isSelected());
                setVisible(false);
            }
        });

        panel.add(label);
        panel.add(nameField);
        panel.add(okButton);
        panel.add(radioButtonPanel);

        getRootPane().setDefaultButton(okButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

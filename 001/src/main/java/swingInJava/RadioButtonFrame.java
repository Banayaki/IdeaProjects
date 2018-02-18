package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonFrame {
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;

    public RadioButtonFrame() {
        label = new JLabel("Better, faster, stronger");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));

        JFrame frame = new JFrame();
        frame.setTitle("Radio Button");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.add(BorderLayout.CENTER, label);

        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Large", 18);
        addRadioButton("Extra large", 36);

        frame.add(BorderLayout.NORTH, buttonPanel);
        frame.setVisible(true);
    }

    public void addRadioButton(String name, final int size){
        boolean selected = (size == DEFAULT_SIZE);
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font("serif", Font.PLAIN, size));
            }
        };

        button.addActionListener(listener);
    }

    public static void main(String[] args) {
        RadioButtonFrame frame = new RadioButtonFrame();
    }
}

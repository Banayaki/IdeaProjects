package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxTest {

    int style = 0;
    JLabel text = new JLabel("Now we should be change a text style");
    JCheckBox boldCheck = new JCheckBox("Bold");
    JCheckBox italicCheck = new JCheckBox("Italic");

    public static void main(String[] args) {
        CheckBoxTest test = new CheckBoxTest();
        test.go();
    }

    public void go(){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setTitle("Test");
        frame.setVisible(true);

        JPanel northPanel = new JPanel();

        boldCheck.addActionListener(new CheckListener());
        italicCheck.addActionListener(new CheckListener());

        northPanel.add(boldCheck);
        northPanel.add(italicCheck);
        frame.add(BorderLayout.NORTH, northPanel);
        frame.add(BorderLayout.CENTER, text);
    }

    class CheckListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            style = 0;
            if (boldCheck.isSelected()) style +=  Font.BOLD;
            if (italicCheck.isSelected()) style += Font.ITALIC;

            text.setFont(new Font("Serif", style, 16));
        }
    }
}
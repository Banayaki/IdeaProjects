package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonChangeColor {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public static void main(String[] args) {
        ButtonChangeColor GUI = new ButtonChangeColor();
        GUI.go();
    }

    public void go(){
        JButton yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(new yellowListener());
        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(new blueListener());
        JButton redButton = new JButton("Red");
        redButton.addActionListener(new redListener());

        frame.setTitle("Change Color");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        frame.getContentPane().add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    class yellowListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setBackground(Color.YELLOW);
        }
    }

    class blueListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setBackground(Color.BLUE);
        }
    }

    class redListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setBackground(Color.RED);
        }
    }
}




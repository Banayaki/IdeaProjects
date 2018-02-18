package main.java.EventHandlingTest;

import javax.swing.*;

public class MouseFrame extends JFrame {
    public MouseFrame(){
        add(new MouseComponent());
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MouseFrame frame = new MouseFrame();
    }
}
